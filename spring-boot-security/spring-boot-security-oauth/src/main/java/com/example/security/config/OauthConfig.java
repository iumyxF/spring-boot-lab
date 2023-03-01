package com.example.security.config;

import com.example.security.api.Github;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.context.annotation.RequestScope;

/**
 * @description:
 * @Date 2023/3/1 14:20
 * @Author fzy
 */
@Slf4j
@Configuration
public class OauthConfig {

    /**
     * github登录点
     */
    private static final String githubRegistration = "github";

    /**
     * 解析accessToken
     *
     * @param clientService
     * @return
     */
    @Bean
    @RequestScope
    public Github github(OAuth2AuthorizedClientService clientService) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String accessToken = null;
        if (authentication.getClass().isAssignableFrom(OAuth2AuthenticationToken.class)) {
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            String clientRegistrationId = oauthToken.getAuthorizedClientRegistrationId();
            if (githubRegistration.equals(clientRegistrationId)) {
                OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(clientRegistrationId, oauthToken.getName());
                if (client != null) {
                    accessToken = client.getAccessToken().getTokenValue();
                }
                log.info(accessToken);
            }
        }
        return new Github(accessToken);
    }
}
