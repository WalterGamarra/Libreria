package com.libreriaApp.libreria.configSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(csrf -> csrf.disable()) // deshabilitás CSRF para APIs REST
                    .authorizeHttpRequests(auth -> auth

                            // Rutas públicas — cualquiera puede consultar sin login
                            .requestMatchers(HttpMethod.GET, "/api/v1/tienda/**").permitAll()

                            // Login y registro — públicos obviamente
                            .requestMatchers("/api/v1/auth/**").permitAll()

                            // Rutas de admin — solo ADMIN
                            .requestMatchers("/api/v1/libros/**").hasRole("ADMIN")
                            .requestMatchers("/api/v1/autores/**").hasRole("ADMIN")
                            .requestMatchers("/api/v1/categorias/**").hasRole("ADMIN")
                            .requestMatchers("/api/v1/editoriales/**").hasRole("ADMIN")

                            // Ventas — solo usuarios autenticados (CLIENTE o ADMIN)
                            .requestMatchers("/api/v1/ventas/**").authenticated()

                            // Cualquier otra ruta no listada → requiere autenticación
                            .anyRequest().authenticated()
                    );
            return http.build();
        }

        @Bean
        public UserDetailsService userDetailsService() {
            // tus usuarios hardcodeados para prueba
            UserDetails user = User.withUsername("Negro")
                    .password("{noop}1234") // {noop} indica que NO está hasheada (solo para pruebas)
                    .roles("USER")
                    .build();

            UserDetails admin = User.withUsername("Azul")
                    .password("{noop}1234")
                    .roles("ADMIN")
                    .build();

            return new InMemoryUserDetailsManager(user, admin);
        }

    @Bean //Crea AuthenticationManager que administra la autenticacion
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration){
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}
