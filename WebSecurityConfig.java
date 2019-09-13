package com.virtusa.banking.configurations;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired	
	private DataSource dataSource;
	
	// Create 2 users for demo
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    		
    	System.out.println("admino");
		/*
		 * auth.inMemoryAuthentication()
		 * .withUser("user").password("{noop}password").roles("USER") .and()
		 * .withUser("admin").password("{noop}password").roles("USER", "ADMIN");
		 */
    	auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select username,password, enabled from virtusausers where username=?").authoritiesByUsernameQuery("select username,role from user_roles wherer username=?").passwordEncoder(new BCryptPasswordEncoder());

    }

    // Secure the endpoins with HTTP Basic authentication
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/login").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }
  
}
