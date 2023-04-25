package com.appdev.legacy.users.webservice.controllers;


import com.appdev.legacy.users.webservice.response.UserRest;
import com.appdev.legacy.users.webservice.response.VerifyPasswordResponse;
import com.appdev.legacy.users.webservice.service.UsersService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UsersController {

    private final UsersService usersService;

    @GetMapping("/{userName}")
    public UserRest getUser(@PathVariable("userName") String userName) {
        log.info("getUser");
        return usersService.getUserDetails(userName);
    }

    @PostMapping("/{userName}/verify-password")
    public VerifyPasswordResponse verifyUserPassword(@PathVariable("userName") String userName,
            @RequestBody String password) {
        log.info("verifyUserPassword");
        VerifyPasswordResponse returnValue = new VerifyPasswordResponse(false);
        UserRest user = usersService.getUserDetails(userName, password);
        if (Objects.isNull(user)) {
            returnValue.setResult(true);
        }
        return returnValue;
    }

}
