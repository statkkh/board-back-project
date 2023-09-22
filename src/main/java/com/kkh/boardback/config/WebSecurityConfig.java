package com.kkh.boardback.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.kkh.boardback.filter.JwtAuthenticationFilter;
import com.mysql.cj.protocol.Security;

import lombok.RequiredArgsConstructor;

@Configurable
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthentication;
    
    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception{
            httpSecurity
                .cors().and()
                .csrf().disable()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/", "api/v1/auth/**", "/api/v1/search/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/board/**").permitAll()
                .anyRequest().authenticated()
                // .exceptionHandling().authenticationEntryPoint(new FailedAuthenticationEntryPoint());

    }   
}
