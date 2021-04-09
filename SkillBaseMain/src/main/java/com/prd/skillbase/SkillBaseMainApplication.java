package com.prd.skillbase;


import com.prd.skillbase.configuration.AuthorizationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;


@SpringBootApplication
public class SkillBaseMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkillBaseMainApplication.class, args);}

		@Bean
		public FilterRegistrationBean<AuthorizationFilter> loggingFilter() {
			FilterRegistrationBean<AuthorizationFilter> registrationBean = new FilterRegistrationBean<>();
			registrationBean.setFilter(new AuthorizationFilter());
			registrationBean.addUrlPatterns("/test/*");
			registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
			return registrationBean;
	}

}
