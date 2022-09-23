package com.example.bunjang.controller;

import com.example.bunjang.common.response.ExceptionResponse;
import com.example.bunjang.common.response.Message;
import com.example.bunjang.dto.*;
import com.example.bunjang.common.exception.DuplicateMemberException;
import com.example.bunjang.jwt.JwtFilter;
import com.example.bunjang.jwt.TokenProvider;
import com.example.bunjang.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
@Log4j2
@RequestMapping("/app")
public class UserController {

    private final UserService userService;

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping(value="/register")
    public ResponseEntity register(@RequestBody RegisterReqDTO registerReqDTO){

        log.info("---------reigster-----------");
        log.info(registerReqDTO);

        userService.register(registerReqDTO);

        Message message = Message.builder()
                .result("Register Success")
                .build();

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO loginDTO) {

        log.info("----------login--------------");
//        log.info(loginDTO);

        // username, password를 파라미터로 받고 이를 이용해 UsernamePasswordAuthenticationToken을 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        Message message = Message.builder()
                .result(new TokenDTO(jwt))
                .build();

        return new ResponseEntity<>(message, HttpStatus.OK);

    }


    //    이것의 위치를 어떻게 할지 고민중우우웅
    @GetMapping("/recent-users")
    public ResponseEntity getRecentUsers(){

        List<RecentUserDTO> result = userService.getRecentUsers();
        Message message = Message.builder()
                .result(result)
                .build();

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //프로필가져오기
//    requestParam? or pathvariable
    @GetMapping(value = {"/my-pages","/users/{userId}"})
    public ResponseEntity getUserProfile( @PathVariable(required = false) Long userId){

        if(userId ==null) {
            userId = userService.findUserId();
        }

        UserDTO result = userService.getProfile(userId);
        Message message = Message.builder()
                .result(result)
                .build();

        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    @ExceptionHandler(DuplicateMemberException.class)
    public ResponseEntity duplicateMemberException(DuplicateMemberException exception) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .code(exception.getHttpStatus().value())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(exceptionResponse, exception.getHttpStatus());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity BadCredentialsException(BadCredentialsException exception) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity usernameNotFoundException(NoSuchElementException exception) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }






}
