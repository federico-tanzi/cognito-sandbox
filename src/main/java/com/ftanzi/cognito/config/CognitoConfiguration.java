package com.ftanzi.cognito.config;

public class CognitoConfiguration {
    private String userPoolId;
    private String clientId;

    public CognitoConfiguration(String userPoolId, String clientId) {
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
