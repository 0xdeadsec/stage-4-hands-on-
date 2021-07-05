package com.cognizant.springlearn2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	 @Bean
	    public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.inMemoryAuthentication()
		.withUser("admin")
		.password(passwordEncoder().encode("pwd"))
		.roles("ADMIN")
		.and()
		.withUser("User")
		.password(passwordEncoder().encode("pwd"))
		.roles("USER");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
	httpSecurity.csrf().disable().httpBasic().and().authorizeRequests()
				.antMatchers("/countries").hasRole("USER")
				.antMatchers("/authenticate")
				.hasAnyRole("USER", "ADMIN")
				.anyRequest().authenticated()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilter(new JwtAuthorizationFilter(authenticationManager()));
    }

   
}
