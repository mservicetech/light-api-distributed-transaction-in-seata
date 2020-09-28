package com.mservicetech.accountservic.model;

import java.util.Arrays;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Transaction  {

    private java.lang.Long accountId;
    
    
    public enum TransactioTypeEnum {
        
        FEE ("FEE"), 
        
        DEPOSIT ("DEPOSIT"), 
        
        PURCHASE ("PURCHASE"); 
        

        private final String value;

        TransactioTypeEnum(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        public static TransactioTypeEnum fromValue(String text) {
            for (TransactioTypeEnum b : TransactioTypeEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                return b;
                }
            }
            return null;
        }
    }

    private TransactioTypeEnum transactioType;

    
    private java.math.BigDecimal amount;
    private String refId;
    private java.lang.Long transactionId;
    private java.time.LocalDate transactioDate;

    public Transaction () {
    }

    @JsonProperty("accountId")
    public java.lang.Long getAccountId() {
        return accountId;
    }

    public void setAccountId(java.lang.Long accountId) {
        this.accountId = accountId;
    }

    @JsonProperty("transactioType")
    public TransactioTypeEnum getTransactioType() {
        return transactioType;
    }

    public void setTransactioType(TransactioTypeEnum transactioType) {
        this.transactioType = transactioType;
    }

    @JsonProperty("amount")
    public java.math.BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(java.math.BigDecimal amount) {
        this.amount = amount;
    }

    @JsonProperty("refId")
    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    @JsonProperty("transactionId")
    public java.lang.Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(java.lang.Long transactionId) {
        this.transactionId = transactionId;
    }

    @JsonProperty("transactioDate")
    public java.time.LocalDate getTransactioDate() {
        return transactioDate;
    }

    public void setTransactioDate(java.time.LocalDate transactioDate) {
        this.transactioDate = transactioDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Transaction Transaction = (Transaction) o;

        return Objects.equals(accountId, Transaction.accountId) &&
               Objects.equals(transactioType, Transaction.transactioType) &&
               Objects.equals(amount, Transaction.amount) &&
               Objects.equals(refId, Transaction.refId) &&
               Objects.equals(transactionId, Transaction.transactionId) &&
               Objects.equals(transactioDate, Transaction.transactioDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, transactioType, amount, refId, transactionId, transactioDate);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Transaction {\n");
        sb.append("    accountId: ").append(toIndentedString(accountId)).append("\n");        sb.append("    transactioType: ").append(toIndentedString(transactioType)).append("\n");        sb.append("    amount: ").append(toIndentedString(amount)).append("\n");        sb.append("    refId: ").append(toIndentedString(refId)).append("\n");        sb.append("    transactionId: ").append(toIndentedString(transactionId)).append("\n");        sb.append("    transactioDate: ").append(toIndentedString(transactioDate)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
