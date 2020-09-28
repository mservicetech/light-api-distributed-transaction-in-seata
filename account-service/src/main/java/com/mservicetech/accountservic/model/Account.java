package com.mservicetech.accountservic.model;

import java.util.Arrays;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Account  {

    private java.math.BigDecimal accountBalance;
    private java.lang.Long accountId;
    private String accountName;

    public Account () {
    }

    @JsonProperty("accountBalance")
    public java.math.BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(java.math.BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    @JsonProperty("accountId")
    public java.lang.Long getAccountId() {
        return accountId;
    }

    public void setAccountId(java.lang.Long accountId) {
        this.accountId = accountId;
    }

    @JsonProperty("accountName")
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Account Account = (Account) o;

        return Objects.equals(accountBalance, Account.accountBalance) &&
               Objects.equals(accountId, Account.accountId) &&
               Objects.equals(accountName, Account.accountName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountBalance, accountId, accountName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Account {\n");
        sb.append("    accountBalance: ").append(toIndentedString(accountBalance)).append("\n");        sb.append("    accountId: ").append(toIndentedString(accountId)).append("\n");        sb.append("    accountName: ").append(toIndentedString(accountName)).append("\n");
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
