package com.ftanzi.cognito.config;

public class PoolConfig {
    private String userPoolId;
    private String clientId;

    public PoolConfig(String userPoolId, String clientId) {
        this.userPoolId = userPoolId;
        this.clientId = clientId;
    }


    public String getUserPoolId() {
        return userPoolId;
    }

    public void setUserPoolId(String userPoolId) {
        this.userPoolId = userPoolId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
