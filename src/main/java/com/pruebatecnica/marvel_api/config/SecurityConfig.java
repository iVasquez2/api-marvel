package com.pruebatecnica.marvel_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

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
            .cors(cors -> cors.configurationSource(corsConfigurationSource())) //  Usar configuración de CORS
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/marvel/characters/*/series/**","/h2-ui/**").permitAll() // Permite acceso sin autenticación
                .requestMatchers("/marvel/**").authenticated() // Protege otras rutas de Marvel
                .anyRequest().permitAll()
            )
            .httpBasic()
            .and()
            .csrf().ignoringRequestMatchers("/h2-ui/**")  // Deshabilita CSRF para la consola H2
            .and()
            .headers().frameOptions().sameOrigin();  // Necesario para permitir iframe de la consola H2
        return http.build(); //  Habilita autenticación Basic Auth

    }

    // 🔹 Método que define la configuración de CORS
    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:4200")); //Permitir Angular
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        config.setAllowCredentials(true);
        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    // 🔹 Filtro de CORS
    @Bean
    public CorsFilter corsFilter() {
        return new CorsFilter(corsConfigurationSource());
    }
}
