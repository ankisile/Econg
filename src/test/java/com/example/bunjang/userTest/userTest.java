package com.example.bunjang.userTest;

import com.example.bunjang.entity.User;
import com.example.bunjang.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class userTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testUserEmail(){
        Optional<User> result = userRepository.findByEmail("abc@gmail.com");

        System.out.println(result);

    }

}
