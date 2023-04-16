package com.example.oauthintro.security;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        Map<String, Object> realmAccess = (Map<String, Object>) source.getClaims().get("realm_access");
        if (Objects.isNull(realmAccess) || realmAccess.isEmpty()) {
            return Collections.emptyList();
        }
        return ((List<String>) realmAccess.get("roles")).stream()
                .map(role -> "ROLE_" + role).map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
