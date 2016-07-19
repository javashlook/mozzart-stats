package com.mozzartbet.stats;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import com.mozzartbet.stats.domain.IPayTransactionRequest;
import com.mozzartbet.stats.domain.IPayTransactionResponse;

/**
 * Set <tt>-Djavax.net.debug=all</tt>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = StatsApplication.class)
@WebAppConfiguration
public class StatsApplicationTests {

	@Inject
	private RestTemplate restTemplate;

	private final String restUri = "https://217.119.241.211:8801/IPay/rest/transfer";

	@Test
	public void accessRest() {
		IPayTransactionRequest rq = new IPayTransactionRequest("test", "test", new Date(), new Long(1L), "TEST", 123.45d, "EUR");
		ResponseEntity<List<IPayTransactionResponse>> re =
				restTemplate.exchange(restUri,
						HttpMethod.POST,
						new HttpEntity<IPayTransactionRequest>(rq),
						new ParameterizedTypeReference<List<IPayTransactionResponse>>() {});
		List<IPayTransactionResponse> r = re.getBody();
		assertNotNull(r);
		System.err.println(r);
	}

}
