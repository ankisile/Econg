package com.example.bunjang.dto;

import lombok.*;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatchProfileDTO {
    private String nickName;

    private String description;

    private String profileUrl;
}
