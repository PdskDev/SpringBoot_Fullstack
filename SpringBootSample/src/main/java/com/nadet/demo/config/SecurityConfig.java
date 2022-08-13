package com.nadet.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SuppressWarnings("deprecation")
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	/** Set a target out-of-security*/
	@Override
	public void configure(WebSecurity web) throws Exception {
		//Do not apply security
		web
		   .ignoring()
		      .antMatchers("/webjars/**")
		      .antMatchers("/css/**")
		      .antMatchers("/js/**")
		      .antMatchers("/h2-console/**");
	}
	
	/** Various security settings */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//Set of login unnecessary page
		http
		   .authorizeRequests()
		      .antMatchers("/login").permitAll() // Direct link Ok
		      .antMatchers("/user/signup").permitAll() // Direct link ok
		      .anyRequest().authenticated(); //Otherwise direct link NG
		
		//Login process
		http
		   .formLogin()
		      .loginProcessingUrl("/login") //Login process path
		      .loginPage("/login") // Specify login page
		      .failureUrl("/login?error") // Transition destination when login fails
		      .usernameParameter("userId") // Login page user Id
		      .passwordParameter("password") // Login page password
		      .defaultSuccessUrl("/user/list", true); // Transition destination after login success
		
		//Disable CSRF measures (temporary)
		http.csrf().disable();
	}
		

}
