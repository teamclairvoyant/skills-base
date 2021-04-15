package com.clairvoyant.clarise.configuration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



@Configuration
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.antMatcher("/**")
//        	.authorizeRequests()
//        	.antMatchers("/employees/test")
//        	.permitAll();
    	
//    	http
//    		.authorizeRequests()
//    		.antMatchers("/employees/test")
//    		.permitAll()
//    		.and()
//    		.oauth2Login();
    	http.authorizeRequests().antMatchers("/employees/test","/api/*").permitAll()
        .anyRequest().authenticated().and().oauth2Login();

    }


}
