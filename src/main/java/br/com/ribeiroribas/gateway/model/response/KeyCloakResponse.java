package br.com.ribeiroribas.gateway.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KeyCloakResponse {

    @JsonProperty("access_token")
    private String accessToken;
    private int expires_in;
    @JsonProperty("refresh_expires_in")
    private int refreshExpiresIn;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("not_before_policy")
    private int notBeforePolicy;
    @JsonProperty("session_state")
    private String sessionState;
    private String scope;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public int getRefreshExpiresIn() {
        return refreshExpiresIn;
    }

    public void setRefreshExpiresIn(int refreshExpiresIn) {
        this.refreshExpiresIn = refreshExpiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public int getNotBeforePolicy() {
        return notBeforePolicy;
    }

    public void setNotBeforePolicy(int notBeforePolicy) {
        this.notBeforePolicy = notBeforePolicy;
    }

    public String getSessionState() {
        return sessionState;
    }

    public void setSessionState(String sessionState) {
        this.sessionState = sessionState;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
