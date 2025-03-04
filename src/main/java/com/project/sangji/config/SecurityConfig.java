package com.project.sangji.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers(
                                "/",
                                "/member/**",
                                "/rest/**",  // REST API 엔드포인트 허용
                                "/intro/**",
                                "/main_business/**",
                                "/procedure/**",
                                "/customers/**",
                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "/fonts/**",
                                "/favicon.ico"
                        ).permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(basic -> basic.disable())
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))
                .formLogin((form) -> form
                        .loginPage("/member/login_main")
                        .loginProcessingUrl("/member/loginOk")
                        .defaultSuccessUrl("/")
                        .failureUrl("/member/login_main?error=true")
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutUrl("/member/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(
                "https://cdn.jsdelivr.net",
                "https://t1.daumcdn.net",
                "https://www.google.com"
        ));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
