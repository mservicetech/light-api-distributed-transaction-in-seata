package com.mservicetech.accountservic;

import java.util.HashMap;
import java.util.Map;

public class AccountConfig {
    public static final String CONFIG_NAME = "account-config";

    private Map<String, String> queryMap = new HashMap<>();


    public AccountConfig() {
    }

    public Map<String, String> getQueryMap() {
        return queryMap;
    }

    public void setQueryMap(Map<String, String> queryMap) {
        this.queryMap = queryMap;
    }
}
