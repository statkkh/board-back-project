package com.kkh.boardback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.beans.factory.annotation.Configurable;

import lombok.RequiredArgsConstructor;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;

import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.core.AuthenticationException;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;

import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import java.io.IOException;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.kkh.boardback.filter.JwtAuthenticationFilter;
import com.kkh.boardback.handler.OAuth2SuccessHandler;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configurable
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final DefaultOAuth2UserService oAuthUserService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
            .cors(cors -> cors
                .configurationSource(corsConfigurationSource())
            )
            .csrf(CsrfConfigurer::disable)
            .httpBasic(HttpBasicConfigurer::disable)
            .sessionManagement(sessionManagement -> sessionManagement
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests(request -> request
                .requestMatchers("/","/api/auth/**", "/oauth2/", "/file/**","/luma/**").permitAll()
                .requestMatchers("/","/api/user/**").hasRole("USER")
                .requestMatchers("/","/api/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .oauth2Login(oauth2->oauth2
                .authorizationEndpoint(endPoint -> endPoint.baseUri("api/auth/oauth2"))
                .redirectionEndpoint(endPoint -> endPoint.baseUri("/oauth2/callback/*"))
                .userInfoEndpoint(endPoint -> endPoint.userService(oAuthUserService))
                .successHandler(oAuth2SuccessHandler)    
            )
            .exceptionHandling(exceptionHandling -> exceptionHandling
                .authenticationEntryPoint(new FailedAuthenticationEntryPoint())
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

                
        return httpSecurity.build();
    }   


    @Bean
    protected CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedMethod("api/**");
        corsConfiguration.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("api/**", corsConfiguration);

        return source;
    } 
}

class FailedAuthenticationEntryPoint implements AuthenticationEntryPoint{

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        
        response.setContentType("application/json");                
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        response.getWriter().write("{\"code\" : \"NP\", \"message\" : \"No Permission.\"}");
    }

}
