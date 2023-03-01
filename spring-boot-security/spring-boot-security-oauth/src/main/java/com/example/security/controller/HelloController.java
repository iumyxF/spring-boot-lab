package com.example.security.controller;

import com.example.security.api.Github;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @Date 2023/3/1 13:52
 * @Author fzy
 */
@Slf4j
@RestController
public class HelloController {

    @Resource
    private ClientRegistrationRepository clientRegistrationRepository;

    @Resource
    private OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    @Resource
    Github github;

    @GetMapping(value = "/")
    public String index() {
        log.info(SecurityContextHolder.getContext().getAuthentication().toString());
        return "Welcome " + SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping(value = "/user/reg")
    public String registration() {
        ClientRegistration githubRegistration = clientRegistrationRepository.findByRegistrationId("github");
        log.info(githubRegistration.toString());
        return githubRegistration.toString();
    }

    /**
     * {
     * "tokenValue":"token value",
     * "issuedAt":"2023-03-01T06:25:21.628Z",
     * "expiresAt":"2023-03-01T06:25:22.628Z",
     * "tokenType":{"value":"Bearer"},
     * "scopes":["read:user"]
     * }
     */
    @GetMapping(value = "/user/token")
    public OAuth2AccessToken accessToken(OAuth2AuthenticationToken authentication) {
        OAuth2AuthorizedClient authorizedClient = oAuth2AuthorizedClientService.loadAuthorizedClient(
                authentication.getAuthorizedClientRegistrationId(), authentication.getName());
        return authorizedClient.getAccessToken();
    }

    @GetMapping(value = "/user/info")
    public String info() {
        String profile = github.getProfile();
        log.info(github.getProfile());
        return profile;
    }
}
