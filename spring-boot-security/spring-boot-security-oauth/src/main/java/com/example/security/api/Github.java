package com.example.security.api;

/**
 * @description:
 * @Date 2023/3/1 14:21
 * @Author fzy
 */
public class Github extends ApiBinding {

    private static final String BASE_URL = "https://api.github.com";

    public Github(String accessToken) {
        super(accessToken);
    }

    public String getProfile() {
        return restTemplate.getForObject(BASE_URL + "/user", String.class);
    }
}
