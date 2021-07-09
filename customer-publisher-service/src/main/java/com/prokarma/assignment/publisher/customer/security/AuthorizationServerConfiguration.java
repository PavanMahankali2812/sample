package com.prokarma.assignment.publisher.customer.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    public static final String CLIENT = "client";
    public static final String CLIENT_PASSWORD = "password";
    public static final String PASSWORD_GRANT_TYPE = "password";
    public static final String AUTH_CODE_GRANT_TYPE = "authorization_code";
    public static final String REFRESH_TOKEN_GRANT_TYPE = "refresh_token";
    public static final String IMPLICIT_GRANT_TYPE = "implicit";
    public static final String READ_SCOPE = "read";
    public static final String WRITE_SCOPE = "write";

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenStore tokenStore;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.inMemory().withClient(CLIENT)
                .authorizedGrantTypes(PASSWORD_GRANT_TYPE, AUTH_CODE_GRANT_TYPE,
                        REFRESH_TOKEN_GRANT_TYPE, IMPLICIT_GRANT_TYPE)
                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT", "USER")
                .scopes(READ_SCOPE, WRITE_SCOPE).autoApprove(true).secret(PasswordEncoderFactories
                        .createDelegatingPasswordEncoder().encode(CLIENT_PASSWORD))
                .accessTokenValiditySeconds(15 * 60);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore);
    }

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }
}
