package com.example.murwa.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UrlReductionCreateRequest {

    @NotNull
    @Size(min = 1)
    private String url;

    private String token;

    UrlReductionCreateRequest() { }

    public UrlReductionCreateRequest(String url) {
        this(url,null);
    }

    public UrlReductionCreateRequest(String url, String token) {
        this.url = url;
        this.token = token;
    }

    public String getToken() {
        return "".equals(token) ? null : token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
