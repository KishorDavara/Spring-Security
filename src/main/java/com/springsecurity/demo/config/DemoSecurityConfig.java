/**
 * 
 */
package com.springsecurity.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

/**
 * @author kdavara
 *
 */

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource securityDataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//use JDBC authentication
		auth.jdbcAuthentication().dataSource(securityDataSource);
		
		// ADD users for in-memory authentication
		/*UserBuilder users = User.withDefaultPasswordEncoder();

		auth.inMemoryAuthentication().withUser(users.username("Kishor").password("user123").roles("EMPLOYEE"))
				.withUser(users.username("Niki").password("user123").roles("EMPLOYEE","MANAGER"))
				.withUser(users.username("John").password("user123").roles("EMPLOYEE","ADMIN"));*/
	}

	/**
	 * authenticate user using custom login form
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		     .antMatchers("/").hasRole("EMPLOYEE")
		     .antMatchers("/leaders/**").hasRole("MANAGER")
		     .antMatchers("/systems/**").hasRole("ADMIN")
		     .and()
		     .formLogin()
		     .loginPage("/showLoginPage")
			 .loginProcessingUrl("/authenticateTheUser")
			 .permitAll()
			 .and()
			 .logout()
			 .permitAll()
			 .and()
			 .exceptionHandling().accessDeniedPage("/access-denied");
	}
}
