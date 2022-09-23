package com.example.bunjang.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@Setter
@ToString
public class RegisterReqDTO {

    private String email;
    private String password;

    private String phoneNumber;

    private String userName;
    private String nickName;

}
