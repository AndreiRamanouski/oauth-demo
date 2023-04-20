package com.example.oauthintro.security;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


//to use @Secured @EnableGlobalMethodSecurity(securedEnabled = true)
//to use @Pre/Post authorize annotations @EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurity {

    private final KeycloakRoleConverter keycloakRoleConverter;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                //.cors().and()
                .authorizeRequests(auth -> {
                            auth.antMatchers("/users/status/check", "/token")
                                    //.hasAuthority("SCOPE_profile");
                                    //.hasRole("developer")
                                    .hasAnyRole("developer", "user");
                            auth.anyRequest().authenticated();
                        }
                )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .oauth2ResourceServer(auth -> auth.jwt().jwtAuthenticationConverter(jwtAuthenticationConverter()))
                .build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(keycloakRoleConverter);
        return jwtAuthenticationConverter;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:8081"));
        corsConfiguration.setAllowedMethods(List.of("POST", "GET"));
        corsConfiguration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
