package com.prokarma.assignment.publisher.customer.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@EnableResourceServer
@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/oauth/token", "/oauth/authorize**").permitAll();

        http.requestMatchers().antMatchers("/customer/*").and().authorizeRequests()
                .antMatchers("/customer/*").access("hasRole('USER')");
    }

}
