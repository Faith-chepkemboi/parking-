package com.example.newparq.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class STKPush {
    @SerializedName("BusinessShortCode")
    @Expose
    public Integer businessShortCode;
    @SerializedName("Password")
    @Expose
    public String password;
    @SerializedName("Timestamp")
    @Expose
    public String timestamp;
    @SerializedName("TransactionType")
    @Expose
    public String transactionType;
    @SerializedName("Amount")
    @Expose
    public Integer amount;
    @SerializedName("PartyA")
    @Expose
    public Long partyA;
    @SerializedName("PartyB")
    @Expose
    public Integer partyB;
    @SerializedName("PhoneNumber")
    @Expose
    public Long phoneNumber;
    @SerializedName("CallBackURL")
    @Expose
    public String callBackURL;
    @SerializedName("AccountReference")
    @Expose
    public String accountReference;
    @SerializedName("TransactionDesc")
    private String transactionDesc;

    private final static long serialVersionUID = -6138796772519026948L;

//    public STKPush(String businessShortCode,
//                   String password, String timestamp,
//                   String transactionType, String valueOf, String sanitizePhoneNumber,
//                   String partyb, String sanitizePhoneNumber1, String callbackurl,
//                   String mpesa_android_test, String testing) {
//    }

    /**
     * No args constructor for use in serialization
     */
    public STKPush() {
    }

//        public Integer getBusinessShortCode() {
//            return businessShortCode;
//        }
//
//        public void setBusinessShortCode(Integer businessShortCode) {
//            this.businessShortCode = businessShortCode;
//        }
//
//
//        public void setPassword(String password) {
//            this.password = password;
//        }
//
//
//        public void setTimestamp(String timestamp) {
//            this.timestamp = timestamp;
//        }
//
//        public String getTransactionType() {
//            return transactionType;
//        }
//
//        public void setTransactionType(String transactionType) {
//            this.transactionType = transactionType;
//        }
//
//        public Integer getAmount() {
//            return amount;
//        }
//
//        public void setAmount(Integer amount) {
//            this.amount = amount;
//        }
//
//        public Long getPartyA() {
//            return partyA;
//        }
//
//        public void setPartyA(Long partyA) {
//            this.partyA = partyA;
//        }
//
//        public Integer getPartyB() {
//            return partyB;
//        }
//
//        public void setPartyB(Integer partyB) {
//            this.partyB = partyB;
//        }
//
//        public Long getPhoneNumber() {
//            return phoneNumber;
//        }
//
//        public void setPhoneNumber(Long phoneNumber) {
//            this.phoneNumber = phoneNumber;
//        }
//
//        public String getCallBackURL() {
//            return callBackURL;
//        }
//
//        public void setCallBackURL(String callBackURL) {
//            this.callBackURL = callBackURL;
//        }
//
//        public String getAccountReference() {
//            return accountReference;
//        }
//
//        public void setAccountReference(String accountReference) {
//            this.accountReference = accountReference;
//        }
//
//        public String getTransactionDesc() {
//            return transactionDesc;
//        }
//
//        public void setTransactionDesc(String transactionDesc) {
//            this.transactionDesc = transactionDesc;
//        }

    /**
     * @param transactionType
     * @param partyA
     * @param password
     * @param amount
     * @param phoneNumber
     * @param callBackURL
     * @param accountReference
     * @param partyB
     * @param businessShortCode
     * @param timestamp
     * @param transactionDesc
     */
    public STKPush(Integer businessShortCode, String password, String timestamp,
                   String transactionType, Integer amount, Long partyA, Integer partyB,
                   Long phoneNumber, String callBackURL, String accountReference, String transactionDesc) {
        super();
        this.businessShortCode = businessShortCode;
        this.password = password;
        this.timestamp = timestamp;
        this.transactionType = transactionType;
        this.amount = amount;
        this.partyA = partyA;
        this.partyB = partyB;
        this.phoneNumber = phoneNumber;
        this.callBackURL = callBackURL;
        this.accountReference = accountReference;
        this.transactionDesc = transactionDesc;
    }

    public STKPush(String businessShortCode, String password, String timestamp, String transactionType, String valueOf, String sanitizePhoneNumber, String partyb, String sanitizePhoneNumber1, String callbackurl, String mpesa_android_test, String testing) {
    }
}

