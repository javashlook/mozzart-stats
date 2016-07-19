package com.mozzartbet.stats.domain;

import java.io.Serializable;
import java.util.Date;

public class IPayTransactionResponse implements Serializable {

    private static final long serialVersionUID = -4575294080869423696L;

    /*
     * "pTransferTran
0020: 73 61 63 74 69 6F 6E 49   64 22 3A 6E 75 6C 6C 2C  sactionId":null,
0030: 22 70 54 72 61 6E 73 61   63 74 69 6F 6E 53 74 61  "pTransactionSta
0040: 74 75 73 22 3A 30 2C 22   70 54 72 61 6E 73 61 63  tus":0,"pTransac
0050: 74 69 6F 6E 44 61 74 65   54 69 6D 65 22 3A 6E 75  tionDateTime":nu
0060: 6C 6C 2C 22 70 45 72 72   6F 72 43 6F 64 65 22 3A  ll,"pErrorCode":
0070: 22 45 52 52 4F 52 5F 43   4F 44 45 5F 43 4C 49 45  "ERROR_CODE_CLIE
0080: 4E 54 5F 41 43 43 45 53   53 5F 44 45 4E 49 45 44  NT_ACCESS_DENIED
0090: 22 2C 22 70 41 63 63 6F   75 6E 74 42 61 6C 61 6E  ","pAccountBalan
00A0: 63 65 22 3A 30 2E 30 7D   5D 64 3B 62 85 B2 7D EC  ce
     */

    private Long pTransferTransactionId;
    private Boolean pTransactionStatus;
    private Date pTransactionDateTime;
    private IPayErrorCodeEnum pErrorCode;
    private Long clientRequestId;
    private Double pAccountBalance;

    public Long getpTransferTransactionId() {
        return pTransferTransactionId;
    }

    public void setpTransferTransactionId(Long transferTransactionId) {
        this.pTransferTransactionId = transferTransactionId;
    }

    public Boolean getpTransactionStatus() {
        return pTransactionStatus;
    }

    public void setpTransactionStatus(Boolean transactionStatus) {
        this.pTransactionStatus = transactionStatus;
    }

    public Date getpTransactionDateTime() {
        return pTransactionDateTime;
    }

    public void setpTransactionDateTime(Date transactionDateTime) {
        this.pTransactionDateTime = transactionDateTime;
    }

    public IPayErrorCodeEnum getpErrorCode() {
        return pErrorCode;
    }

    public void setpErrorCode(IPayErrorCodeEnum errorCode) {
        this.pErrorCode = errorCode;
    }

    public Double getpAccountBalance() {
        return pAccountBalance;
    }

    public void setpAccountBalance(Double accountBalance) {
        this.pAccountBalance = accountBalance;
    }

    public Long getClientRequestId() {
        return clientRequestId;
    }

    public void setClientRequestId(Long clientRequestId) {
        this.clientRequestId = clientRequestId;
    }

    @Override
    public String toString() {
        return "IPayTransactionResponse [transferTransactionId=" + pTransferTransactionId + ", transactionStatus=" + pTransactionStatus
                + ", transactionDateTime=" + pTransactionDateTime + ", errorCode=" + pErrorCode + ", clientRequestId=" + clientRequestId
                + ", accountBalance=" + pAccountBalance + "]";
    }



}