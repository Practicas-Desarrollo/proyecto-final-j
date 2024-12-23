package com.taller1.proy1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//import com.taller1.proy1.config.JWTAuthorizationFilter;


@SpringBootApplication
public class Proy1Application {

	public static void main(String[] args) {
		SpringApplication.run(Proy1Application.class, args);
	}

	//@EnableWebSecurity
//	@Configuration
//	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
	//		http.csrf().disable()
//				.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
	//			.authorizeRequests()
	//			.antMatchers(HttpMethod.POST, "/api/login").permitAll()
	//			.anyRequest().authenticated();
	//	}
//	}
	
}
