package com.example.bunjang.controller;

import com.example.bunjang.common.exception.UserNotDefinedException;
import com.example.bunjang.common.response.ExceptionResponse;
import com.example.bunjang.common.response.Message;
import com.example.bunjang.dto.FollowDTO;
import com.example.bunjang.service.FollowService;
import com.example.bunjang.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Log4j2
@RequestMapping("/app")
public class FollowingController {

    private final UserService userService;
    private final FollowService followService;

    @PostMapping("/follows")
    public ResponseEntity pushFollows(@RequestBody FollowDTO followDTO){
        Long userId = userService.findUserId();
        String str = followService.follow(userId, followDTO);
        Message message = Message.builder()
                .result(str)
                .build();

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping(value = {"/followers","/users/{userId}/followers"})
    public ResponseEntity getFollowers(@PathVariable(required = false) Long userId){

        if(userId ==null || userId == userService.findUserId()) {
            userId = userService.findUserId();
        }
        List<FollowDTO> followDTOList = followService.getFollowers(userId);

        Message message = Message.builder()
                .result(followDTOList)
                .build();

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping(value = {"/followings","/users/{userId}/followings"})
    public ResponseEntity getFollowings(@PathVariable(required = false) Long userId){

        if(userId ==null || userId == userService.findUserId()) {
            userId = userService.findUserId();
        }

        List<FollowDTO> followDTOList = followService.getFollowings( userId);

        Message message = Message.builder()
                .result(followDTOList)
                .build();

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @ExceptionHandler(UserNotDefinedException.class)
    public ResponseEntity userNotDefinedException(UserNotDefinedException exception) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
