package com.example.oauthintro.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("users")
public class UsersController {

    @GetMapping("status/check")
    public String status(@AuthenticationPrincipal Jwt jwt) {

        log.info("User Id {}", jwt.getSubject());
        log.info("Claims {}", jwt.getClaims());
        log.info("Get status");
        return "Working...";
    }

    //    uses hasAuthority. Therefore, add the prefix ROLE_
    @Secured("ROLE_developer")
    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable(name = "id") String id) {
        log.info("Delete user {}", id);
        return "Delete user " + id;
    }

    //    @PreAuthorize("hasRole('developer')")
    //    @PreAuthorize("hasAuthority('ROLE_developer')")
    @PreAuthorize("hasAuthority('ROLE_developer') or #id == #jwt.subject")
    //    @PostAuthorize("")
    @GetMapping("{id}")
    public String getUser(@PathVariable(name = "id") String id, @AuthenticationPrincipal Jwt jwt) {
        log.info("Get user {}", id);
        return "Get user " + id + " and jwt subject " + jwt.getSubject();
    }


    @PostAuthorize("returnObject.userId == #jwt.subject")
    @PostMapping
    public UserResponse createUser(@AuthenticationPrincipal Jwt jwt) {
        log.info("Create user {}", jwt.getSubject());
        return UserResponse.builder()
                .firstName("Andrei")
                .lastName("Ramanouski")
                .userId(jwt.getSubject())
                .build();
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Data
    public static class UserResponse {

        private String firstName;
        private String lastName;
        private String userId;
    }
}
