package com.appdev.social.login.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurity {

    private final ClientRegistrationRepository clientRegistrationRepository;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests(auth -> {
                            auth.antMatchers("/").permitAll();
                            auth.anyRequest().authenticated();
                        }
                )
                .oauth2Login(httpSecurityOAuth2LoginConfigurer -> {
                    try {
                        httpSecurityOAuth2LoginConfigurer.init(http);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .logout(httpSecurityLogoutConfigurer -> {
//                    httpSecurityLogoutConfigurer.logoutSuccessUrl("/");
                    httpSecurityLogoutConfigurer.logoutSuccessHandler(oidcLogoutSuccessHandler());
                    httpSecurityLogoutConfigurer.invalidateHttpSession(true);
                    httpSecurityLogoutConfigurer.clearAuthentication(true);
                    httpSecurityLogoutConfigurer.deleteCookies("JSESSIONID");
                })
                .build();
    }

    private OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler() {
        OidcClientInitiatedLogoutSuccessHandler successHandler = new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
        successHandler.setPostLogoutRedirectUri("http://localhost:8080/");
        return successHandler;
    }

}
