package com.filmfusion.securityconfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
		
		httpSecurity
					.csrf(csrf->csrf.disable())
					.authorizeHttpRequests(auth->auth
							.requestMatchers(HttpMethod.GET ,"/bollywood/**" , "/", "/index.html", "/css/**").permitAll()
							.anyRequest().authenticated()
							)
					.httpBasic();
		return httpSecurity.build();
	}
	
}
