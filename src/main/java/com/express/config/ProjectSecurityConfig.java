package com.express.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    // ref: SpringBootWebSecurityConfiguration
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        /*
        * Custom security configuration
        * */
        http.authorizeHttpRequests()
                .requestMatchers("/myAccount","/myBalance","/myCards","/myLoans").authenticated()
                .requestMatchers("/contact","/notices").permitAll()
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
}
