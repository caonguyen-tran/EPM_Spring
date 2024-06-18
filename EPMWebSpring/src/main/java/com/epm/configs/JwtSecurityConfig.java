/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.configs;

import com.epm.filters.CustomAccessDeniedHandler;
import com.epm.filters.JwtAuthenticationTokenFilter;
import com.epm.filters.RestAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author ACER
 */
@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.epm.controllers",
    "com.epm.repositories",
    "com.epm.services",
    "com.epm.components",
    "com.epm.mapper"
})
@Order(1)
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
        JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
        jwtAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
        return jwtAuthenticationTokenFilter;
    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/api/**");

        http.authorizeRequests().antMatchers("/api/login/").permitAll();
        http.authorizeRequests().antMatchers("/api/user/login/").permitAll();
        http.authorizeRequests().antMatchers("/api/process_register/").permitAll();
        http.authorizeRequests().antMatchers("/api/activities/").permitAll();
        http.authorizeRequests().antMatchers("/api/activities/{activityId}/").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/comments/").permitAll();
        http.antMatcher("/api/**").httpBasic().authenticationEntryPoint(restServicesEntryPoint()).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers(HttpMethod.DELETE, "/api/**").hasAuthority("ROLE_ADMIN").and()
                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());

        http.authorizeRequests()
                .antMatchers("/api/activities/**").hasAnyAuthority("ROLE_ASSISTANT", "ROLE_STUDENT")
                .antMatchers("/api/activities/create").hasAuthority("ROLE_ASSISTANT")
                .antMatchers("/api/admin/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/api/assistant/**", "/api/join-activity/**").hasAuthority("ROLE_ASSISTANT")
                .antMatchers("/api/comments/**", "/api/likes/**", "/api/missing-report/**", "/api/score/**", "/api/user/current-user/**").permitAll()
                .antMatchers("/api/report/**", "/api/score-student/**", "/api/statistics/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_ASSISTANT")
                .anyRequest().authenticated();
    }
}
