package com.mozzartbet.stats;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Throwables;

@Configuration
public class RestConfiguration {

	private final int timeout = 5000;
	private final int maxConnTotal = 1;
	private final int retryCount = 5;

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		converters.add(new MappingJackson2HttpMessageConverter());
		restTemplate.setMessageConverters(converters);
		return restTemplate;
	}

	@Bean
	public ClientHttpRequestFactory getClientHttpRequestFactory() {
		RequestConfig config = RequestConfig.custom()
				.setConnectTimeout(timeout)
				.setSocketTimeout(timeout)
				.setConnectionRequestTimeout(timeout)
				.build();

		CloseableHttpClient client = HttpClientBuilder.create()
				.setDefaultRequestConfig(config)
				.setMaxConnTotal(maxConnTotal)
				.setRetryHandler(getRetryHandler())
				.setConnectionManager(sslConnectionManager())
				.build();

		return new HttpComponentsClientHttpRequestFactory(client);
	}

	private HttpClientConnectionManager sslConnectionManager() {
		final String password = "mozzart";
		final String jks = "classpath:security/paygw.jks";

		try {
			SSLContext c = SSLContexts
					.custom()
					.loadKeyMaterial(ResourceUtils.getURL(jks),
							password.toCharArray(), password.toCharArray())
					.build();

			SSLConnectionSocketFactory sf = new SSLConnectionSocketFactory(c,
					new String[] { "TLSv1.2" },
					null,
					new DefaultHostnameVerifier());

			Registry<ConnectionSocketFactory> sfr = RegistryBuilder.<ConnectionSocketFactory> create()
					.register("https", sf)
					.build();

			return new PoolingHttpClientConnectionManager(sfr);
		} catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException
				| UnrecoverableKeyException | CertificateException | IOException e) {
			throw Throwables.propagate(e);
		}
	}

	private HttpRequestRetryHandler getRetryHandler() {
		return new DefaultHttpRequestRetryHandler(retryCount, false);
	}
}