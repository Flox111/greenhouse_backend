package com.maltsev.greenhouse.config.jwt;

public class TokenRefreshRequest {

    private String accessToken;
    private String refreshToken;

    public String getRefreshToken(){
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
