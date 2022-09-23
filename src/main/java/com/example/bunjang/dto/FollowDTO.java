package com.example.bunjang.dto;

import lombok.*;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FollowDTO {
    private Long userId;
    private String userName;
    private String profileUrl;
    private boolean follow;

}
