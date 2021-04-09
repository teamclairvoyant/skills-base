package com.clairvoyant.clarise;

import com.clairvoyant.clarise.configuration.AuthorizationFilter;
import com.clairvoyant.clarise.configuration.SimpleCORSFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;


@SpringBootApplication
public class ClariseMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClariseMainApplication.class, args); }

	@Bean
	public FilterRegistrationBean<AuthorizationFilter> loggingFilter() {
		FilterRegistrationBean<AuthorizationFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new AuthorizationFilter());
		registrationBean.addUrlPatterns("/employees/test");
		registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return registrationBean;
	}

	@Bean
	public FilterRegistrationBean<SimpleCORSFilter> corsFilter() {
		FilterRegistrationBean<SimpleCORSFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new SimpleCORSFilter());
//		registrationBean.addUrlPatterns("/api/*");
//		registrationBean.addUrlPatterns("/assets/*");
//		registrationBean.addUrlPatterns("/v2/assets/*");
		registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return registrationBean;
	}


}
