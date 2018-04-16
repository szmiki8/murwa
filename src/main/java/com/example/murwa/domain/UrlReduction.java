package com.example.murwa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UrlReduction {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String token;

    @Column(nullable = false, length = 1000)
    private String url;

    UrlReduction() { }

    public UrlReduction(String url) {
        this.url = url;
    }

    public UrlReduction(String url, String token) {
        this.url = url;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String getUrl() {
        return url;
    }

}
