package com.clairvoyant.clarise;

import com.clairvoyant.clarise.configuration.AuthorizationFilter;
import com.clairvoyant.clarise.configuration.SimpleCORSFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.Ordered;


@SpringBootApplication
@EnableAspectJAutoProxy
public class ClariseMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClariseMainApplication.class, args); }

	@Bean
	public FilterRegistrationBean<AuthorizationFilter> loggingFilter() {
		FilterRegistrationBean<AuthorizationFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new AuthorizationFilter());
		registrationBean.addUrlPatterns("/graphql/*");
		registrationBean.addUrlPatterns("/api/*");
		registrationBean.addUrlPatterns("/v1/*");
//		registrationBean.addUrlPatterns("/employees/user/test");
//		registrationBean.addUrlPatterns("/skills");
//		registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return registrationBean;
	}

	@Bean
	public FilterRegistrationBean<SimpleCORSFilter> corsFilter() {
		FilterRegistrationBean<SimpleCORSFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new SimpleCORSFilter());
		registrationBean.addUrlPatterns("/graphql/*");
		registrationBean.addUrlPatterns("/api/*");
		registrationBean.addUrlPatterns("/v1/*");
//		registrationBean.addUrlPatterns("/employees/user/test");
//		registrationBean.addUrlPatterns("/skills");
//		registrationBean.addUrlPatterns("/addcategory");
//		registrationBean.addUrlPatterns("/assets/*");
//		registrationBean.addUrlPatterns("/v2/assets/*");
		registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return registrationBean;
	}
}
