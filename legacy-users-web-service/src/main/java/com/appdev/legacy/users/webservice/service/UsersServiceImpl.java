package com.appdev.legacy.users.webservice.service;

import com.appdev.legacy.users.webservice.data.UserEntity;
import com.appdev.legacy.users.webservice.data.UsersRepository;
import com.appdev.legacy.users.webservice.response.UserRest;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    
    @Override
    public UserRest getUserDetails(String userName) {
        log.info("getUserDetails");
        UserRest returnValue = new UserRest();
        UserEntity userEntity = usersRepository.findByEmail(userName);
        if (Objects.isNull(userEntity)) {
            return returnValue;
        }
        BeanUtils.copyProperties(userEntity, returnValue);
        return returnValue;
    }

    @Override
    public UserRest getUserDetails(String userName, String password) {
        log.info("getUserDetails");
        UserEntity userEntity = usersRepository.findByEmail(userName);
        if (Objects.isNull(userEntity)) {
            return null;
        }
        if (bCryptPasswordEncoder.matches(password,
                userEntity.getEncryptedPassword())) {
            System.out.println("password matches!!!");
            UserRest returnValue = new UserRest();
            BeanUtils.copyProperties(userEntity, returnValue);
            return returnValue;
        }
        return null;
    }

}
