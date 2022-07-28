package com.example.bunjang.controller;

import com.example.bunjang.dto.LoginDTO;
import com.example.bunjang.dto.RegisterReqDTO;
import com.example.bunjang.dto.TokenDTO;
import com.example.bunjang.jwt.JwtFilter;
import com.example.bunjang.jwt.TokenProvider;
import com.example.bunjang.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Log4j2
@RequestMapping("/app")
public class UserController {

    private final UserService userService;

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping(value="/register")
    public ResponseEntity<String> register(@RequestBody RegisterReqDTO registerReqDTO){

        log.info("---------reigster-----------");
        log.info(registerReqDTO);

        userService.register(registerReqDTO);

        return new ResponseEntity<>("registered", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO loginDTO) {

        log.info("----------login--------------");
        log.info(loginDTO);

        // username, password를 파라미터로 받고 이를 이용해 UsernamePasswordAuthenticationToken을 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new TokenDTO(jwt), httpHeaders, HttpStatus.OK);
    }

    //사용자 info => 내 정보 가져오기
    @GetMapping("/users/info")
    public ResponseEntity<?> getMyInfo() {
        return ResponseEntity.ok(userService.findUserInfo());
    }

}
