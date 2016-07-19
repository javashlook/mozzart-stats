package com.mozzartbet.stats.domain;

import java.io.Serializable;
import java.util.Date;

public class IPayTransactionRequest implements Serializable {

	private static final long serialVersionUID = 6218764979403378760L;

	private String clientUsername;
	private String clientPassword;
	private Date clientRequestDateTime;
	private Long clientRequestId;
	private String accountSerialNo;
	private Double transactionAmount;
	private String currency;

	public IPayTransactionRequest() {
	}

	public IPayTransactionRequest(String clientUsername, String clientPassword, Date clientRequestDateTime,
			Long clientRequestId, String accountSerialNo, Double transactionAmount, String currency) {
		super();
		this.clientUsername = clientUsername;
		this.clientPassword = clientPassword;
		this.clientRequestDateTime = clientRequestDateTime;
		this.clientRequestId = clientRequestId;
		this.accountSerialNo = accountSerialNo;
		this.transactionAmount = transactionAmount;
		this.currency = currency;
	}


	//

	public String getClientUsername() {
		return clientUsername;
	}

	public void setClientUsername(String clientUsername) {
		this.clientUsername = clientUsername;
	}

	public String getClientPassword() {
		return clientPassword;
	}

	public void setClientPassword(String clientPassword) {
		this.clientPassword = clientPassword;
	}

	public Date getClientRequestDateTime() {
		return clientRequestDateTime;
	}

	public void setClientRequestDateTime(Date clientRequestDateTime) {
		this.clientRequestDateTime = clientRequestDateTime;
	}

	public Long getClientRequestId() {
		return clientRequestId;
	}

	public void setClientRequestId(Long clientRequestId) {
		this.clientRequestId = clientRequestId;
	}

	public String getAccountSerialNo() {
		return accountSerialNo;
	}

	public void setAccountSerialNo(String accountSerialNo) {
		this.accountSerialNo = accountSerialNo;
	}

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
