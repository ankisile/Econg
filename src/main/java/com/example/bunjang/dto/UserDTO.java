package com.example.bunjang.dto;

import lombok.*;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserDTO {

     private Long userId;

    private String nickName;

     private String description;

    private String profileUrl;

     private Boolean authenticate;

    private Long followingNum;

    private Long followerNum;


}
