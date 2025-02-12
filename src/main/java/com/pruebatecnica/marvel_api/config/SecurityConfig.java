package com.pruebatecnica.marvel_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User.withDefaultPasswordEncoder() // Usamos 'withDefaultPasswordEncoder' para pruebas
        .username("admin")  // Nombre de usuario
        .password("8956")  // Contraseña sin cifrado (para pruebas)
        .roles("ADMIN")  // Rol asignado al usuario
        .build();
return new InMemoryUserDetailsManager(user);

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/marvel/**", "/h2-ui/**", "/consultas/**").authenticated()  // Rutas protegidas por autenticación
                                .anyRequest().permitAll()  // Rutas públicas
                )
                .httpBasic(basic -> {})  // Habilita la autenticación básica
                .csrf(csrf -> csrf.disable());  // Permite el uso de iframe para la consola H2

        return http.build();
    }
}
