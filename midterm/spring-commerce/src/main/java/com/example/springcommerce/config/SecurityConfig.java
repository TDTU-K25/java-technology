package com.example.springcommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors(Customizer.withDefaults())
                .authorizeRequests()
                    .requestMatchers("/checkout").authenticated() // checkout out order need to login
                    .requestMatchers(HttpMethod.GET, "/api/**").permitAll()
//                    .requestMatchers("/api/**").hasAuthority("ADMIN")
                    .anyRequest().permitAll()
                .and().formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/authenticateTheUser")
                    .successHandler(new MyAuthenticationSuccessHandler())
//                    .defaultSuccessUrl("/")
                    .permitAll()
                .and().logout()
                    .logoutSuccessHandler(new MyLogoutSuccessHandler())
                    .permitAll()
                .and()
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(Customizer.withDefaults());
//                .and().exceptionHandling().accessDeniedPage("/access-denied")

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
