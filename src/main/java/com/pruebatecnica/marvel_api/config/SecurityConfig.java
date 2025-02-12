package com.pruebatecnica.marvel_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public UserDetailsService userDetailsService(){
        return new InMemoryUserDetailsManager(
            User.withUsername("admin")
                .password("{noop}8956")
                .roles("ADMIN")
                .build()
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(requests -> requests
                        .requestMatchers("/marvel/**","/h2-ui/**").permitAll()  // Permite el acceso a la consola H2 sin autenticación
                        .requestMatchers("/**").authenticated())
                .csrf(csrf -> csrf.disable())  // Desactiva CSRF (necesario para la consola H2)
                .headers(headers -> headers.frameOptions().disable()); 
        return http.build();
    }
}
