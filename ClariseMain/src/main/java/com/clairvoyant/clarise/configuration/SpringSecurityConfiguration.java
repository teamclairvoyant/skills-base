package com.clairvoyant.clarise.configuration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {

    	http.authorizeRequests()
                .antMatchers("/api/*", "/v1/**", "/graphql").permitAll()
                //.antMatchers("/graphql").permitAll()

                //authorize requests from graphql related apps that we will need
                .antMatchers("/graphiql").permitAll()
                .antMatchers("/vendor/**").permitAll()
        .anyRequest().authenticated().and().oauth2Login().and().csrf().disable();
/*
   		  http.authorizeRequests().antMatchers("/api/*", "/v1/**").permitAll()
		  .anyRequest().authenticated().and().oauth2Login().and().csrf().disable();*/

    	/*
            .antMatchers("/admin").hasRole("ADMIN")
            .antMatchers("/user").hasRole("ADMIN","USER")
            .antMatchers("/").permitAll()
        */
    }
}
