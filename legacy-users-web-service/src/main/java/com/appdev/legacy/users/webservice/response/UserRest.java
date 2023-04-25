package com.appdev.legacy.users.webservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRest {
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String userId;

}
