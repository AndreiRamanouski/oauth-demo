package com.appdev.legacy.users.webservice.service;

import com.appdev.legacy.users.webservice.response.UserRest;

public interface UsersService {
   UserRest getUserDetails(String userName, String password);
   UserRest getUserDetails(String userName);
}
