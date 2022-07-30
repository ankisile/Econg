package com.example.bunjang.dto;

import com.example.bunjang.entity.User;
import lombok.Getter;

@Getter
public class UserResDTO {
    private String email;
    private String userName;
    private String imgUrl;

    public UserResDTO(User user) {
        this.email = user.getEmail();
        this.userName = user.getUserName();
        this.imgUrl = user.getProfileUrl();
    }
}
