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
      
        http.authorizeRequests().antMatchers("/api/login/").permitAll()
                .antMatchers("/api/user/login/").permitAll()
                .antMatchers("/api/process_register/").permitAll()
                .antMatchers("/api/comments/**").permitAll()
                .antMatchers("/api/likes/**").permitAll()
                .antMatchers("/api/register/**").permitAll()
                .antMatchers("/api/semesters/**").permitAll()
                .antMatchers("/api/faculties/**").permitAll()
                .antMatchers("/api/terms/**").permitAll();
      
        http.antMatcher("/api/**").httpBasic().authenticationEntryPoint(restServicesEntryPoint()).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/activities/joined/", "/api/activities/", "/api/activities/{id}", "/api/activities/all/").permitAll()
                .antMatchers(HttpMethod.POST, "/api/activities/create/", "/api/activities/update/{id}").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_ASSISTANT')")
                .antMatchers(HttpMethod.DELETE, "/api/activities/delete/{id}").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_ASSISTANT')")
                .antMatchers(HttpMethod.GET, "/api/join-activity/{activityId}").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.POST, "/api/join-activity/{joinActivityId}").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_ASSISTANT') or hasRole('ROLE_STUDENT')")
                .antMatchers(HttpMethod.DELETE, "/api/join-activity/{joinId}").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_ASSISTANT')")
                .antMatchers(HttpMethod.GET, "/api/missing-report/get-missing-report-of-student/", "/api/missing-report/faculty/{facultyId}").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_ASSISTANT')")
                .antMatchers(HttpMethod.POST, "/api/missing-report/create").access("hasRole('ROLE_STUDENT')")
                .antMatchers(HttpMethod.GET, "/api/register/personal-register/{userId}").access("hasRole('ROLE_STUDENT') or hasRole('ROLE_ASSISTANT')")
                .antMatchers(HttpMethod.POST, "/api/register/").access("hasRole('ROLE_STUDENT') or hasRole('ROLE_ASSISTANT')")
                .antMatchers(HttpMethod.DELETE, "/api/register/{registerId}").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_ASSISTANT') or hasRole('ROLE_STUDENT')")
                .antMatchers("/api/report/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_ASSISTANT')")
                .antMatchers("/api/score/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_ASSISTANT') or hasRole('ROLE_STUDENT')")
                .antMatchers(HttpMethod.GET, "/api/score-student/result").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_ASSISTANT') or hasRole('ROLE_STUDENT')")
                .antMatchers(HttpMethod.POST, "/api/score-student/accept", "/api/score-student/upload-csv").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_ASSISTANT')")
                .antMatchers(HttpMethod.GET, "/api/statistics/class/{classId}/achievements").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_ASSISTANT')")
                .antMatchers(HttpMethod.GET, "/api/user/verify").permitAll()
                .antMatchers(HttpMethod.GET, "/api/user/current-user").authenticated()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
    }
}
