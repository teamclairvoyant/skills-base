package com.clairvoyant.clarise;

import com.clairvoyant.clarise.configuration.AuthorizationFilter;
import com.clairvoyant.clarise.configuration.SimpleCorsFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClariseMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClariseMainApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean<AuthorizationFilter> loggingFilter() {
        FilterRegistrationBean<AuthorizationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthorizationFilter());
        registrationBean.addUrlPatterns("/api/*");
        registrationBean.addUrlPatterns("/v1/*");

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<SimpleCorsFilter> corsFilter() {
        FilterRegistrationBean<SimpleCorsFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new SimpleCorsFilter());
        registrationBean.addUrlPatterns("/api/*");
        registrationBean.addUrlPatterns("/v1/*");
        registrationBean.addUrlPatterns("/employees/user/test");

        return registrationBean;
    }
}
