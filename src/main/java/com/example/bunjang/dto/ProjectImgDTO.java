package com.example.bunjang.dto;


import lombok.*;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectImgDTO {

    private long projectImgId;
    private String projectImgUrl;
}
