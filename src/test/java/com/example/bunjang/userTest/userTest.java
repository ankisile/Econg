package com.example.bunjang.userTest;

import com.example.bunjang.common.Role;
import com.example.bunjang.entity.User;
import com.example.bunjang.repository.UserRepository;
import com.example.bunjang.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class userTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void testInsertUser(){
        User user = User.builder()
                .userName("에코2")
                .email("eco2@gmail.com")
                .password("eco1234!")
                .phone("022465843")
                .activated(true)
                .role(Role.ROLE_ADMIN)
                .build();

        userRepository.save(user);
    }

    @Test
    public void testUserEmail(){
        Optional<User> result = userRepository.findByEmail("abc@gmail.com");

        System.out.println(result);

    }

    @Test
    public void testAdmin(){
        List<User> user = userRepository.findByRole(Role.ROLE_ADMIN);

        for( User u : user){
            System.out.println(u);
        }

    }
}
