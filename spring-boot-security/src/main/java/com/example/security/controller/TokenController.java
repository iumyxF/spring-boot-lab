package com.example.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.stream.Collectors;

/**
 * @description: A controller for the token resource
 * @Date 2023/2/16 14:28
 * @Author fzy
 */
@RestController
public class TokenController {

    @Autowired
    private JwtEncoder encoder;

    /**
     * 获取token凭证
     * jwt token = base64 URL(头部) + "." +base64 URL(负载) + "." + base64 URL(签名)
     * 头部：{"alg":"HS256","typ":"JWT"}alg是签名用的算法，typ属性表示令牌的类型，JWT令牌统一写为JWT
     * 负载：iss-发行人；exp-到期时间；sub-主题；nbf-在此之前不可用；iat-发布时间；jti-JWT ID用于标识JWT
     * 负载除了默认字段外，还可以自定义字段
     */
    @PostMapping("/token")
    public String token(Authentication authentication) {
        Instant now = Instant.now();
        long expiry = 36000L;
        // @formatter:off
        // 获取权限列表[xx xx ..]
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        //JWT生成
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();
        // @formatter:on
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }


}
