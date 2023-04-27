package com.express.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.Collections;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    // ref: SpringBootWebSecurityConfiguration
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        /*
        * Custom security configuration
        * */
        http.cors().configurationSource(request -> {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
            configuration.setAllowedMethods(Collections.singletonList("*"));
            configuration.setAllowCredentials(true);
            configuration.setAllowedHeaders(Collections.singletonList("*"));
            configuration.setMaxAge(3600L);
            return configuration;
        })
                .and().csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/myAccount","/myBalance","/myCards","/myLoans").authenticated()
                .requestMatchers("/contact","/notices","/register").permitAll()
                .and().formLogin()
                .and().httpBasic();

        /*
        * Configuration to deny all the requests coming towards the application
        * */
//        http.authorizeHttpRequests()
//                .anyRequest().denyAll()
//                .and().formLogin()
//                .and().httpBasic();


        /*
         * Configuration to permit all the requests coming towards the application
         * */
//        http.authorizeHttpRequests()
//                .anyRequest().permitAll()
//                .and().formLogin()
//                .and().httpBasic();

        /**
         * Configuration to authenticate every request : default behavior use by spring security
         */
//        http.authorizeHttpRequests()
//                .anyRequest().authenticated()
//                .and().formLogin()
//                .and().httpBasic();

        return http.build();
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//        //Approach 1: here we use withDefaultPasswordEncoder() method while creating the user details
//        /*UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("12345")
//                .authorities("admin")
//                .build();
//
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("12345")
//                .authorities("read")
//                .build();
//        return new InMemoryUserDetailsManager(admin,user); */
//
//        //Approach 2: here we use NoOpPasswordEncoder bean while creating the user details
//        UserDetails admin = User
//                .withUsername("admin")
//                .password("12345")
//                .authorities("admin")
//                .build();
//
//        UserDetails user = User
//                .withUsername("user")
//                .password("12345")
//                .authorities("read")
//                .build();
//        return new InMemoryUserDetailsManager(admin,user);
//    }


// This will create conflict with Custom UserDetailsService implementation
//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
