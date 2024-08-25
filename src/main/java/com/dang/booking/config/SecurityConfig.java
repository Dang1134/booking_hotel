package com.dang.booking.config;

import com.dang.booking.filter.CustomFilterSecurity;
import com.dang.booking.security.CustomAuthenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, CustomAuthenProvider customAuthenProvider) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(customAuthenProvider)
                .build();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, ApiProperties apiProperties, CustomFilterSecurity customFilterSecurity) throws Exception {
        String basePath = apiProperties.getBasePath();
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(author -> {
                    author.requestMatchers( basePath + "/auth/login").permitAll();

                    author.requestMatchers(basePath+"/admin/**").hasRole("ADMIN");
                    author.requestMatchers(basePath+"/hotel/me",basePath+"/review/reply", basePath + "/review/hotelier/**", basePath + "/booking/hotel/**").permitAll();

                    author.requestMatchers(basePath+ "/hotel/all",basePath +"/hotel/{id}").permitAll();
                    author.requestMatchers(basePath+ "/user").permitAll();
                    author.requestMatchers(HttpMethod.POST,basePath+"/user/signup").permitAll();
                    author.requestMatchers(HttpMethod.POST ,"typeroom").hasRole("HOTEL_OWNER");
                  //  author.requestMatchers(HttpMethod.DELETE, "typeroom").hasRole("HOTEL_OWNER");


                    author.anyRequest().authenticated();
                })
                .addFilterBefore(customFilterSecurity, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
