package com.appdev.legacy.users.webservice;

import com.appdev.legacy.users.webservice.data.UserEntity;
import com.appdev.legacy.users.webservice.data.UsersRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitialSetup {


    private final UsersRepository usersRepository;


    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @EventListener
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {
        UserEntity user = new UserEntity(
                1L,
                "qswe3mg84mfjtu",
                "Andrei",
                "Ramanouski",
                "test2@gmail.com",
                bCryptPasswordEncoder.encode("3424202"),
                "",
                false);

        usersRepository.save(user);
    }
}
