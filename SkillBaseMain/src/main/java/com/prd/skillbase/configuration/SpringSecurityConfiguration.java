package com.prd.skillbase.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.antMatcher("/**").authorizeRequests().antMatchers("/test").permitAll().anyRequest()
                .authenticated().and().oauth2Login();
    }
}