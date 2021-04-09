package com.clairvoyant.clarise;

import com.clairvoyant.clarise.configuration.AuthorizationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;


@SpringBootApplication
public class SkillBaseMainApplication  {

	public static void main(String[] args) {
		SpringApplication.run(SkillBaseMainApplication.class, args); }

	@Bean
	public FilterRegistrationBean<AuthorizationFilter> loggingFilter() {
		FilterRegistrationBean<AuthorizationFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new AuthorizationFilter());
		registrationBean.addUrlPatterns("/employees/test");
		registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return registrationBean;
	}

}
