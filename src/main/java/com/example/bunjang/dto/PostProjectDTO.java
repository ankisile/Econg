package com.example.bunjang.dto;

import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostProjectDTO {

    private String title;

    private LocalDate openingDate;

    private LocalDate closingDate;

    private int goalAmount;

    private String summary;

    private String thumbnail;

    private String content;

    private List<ProjectImgDTO> projectImgList;

    private List<RewardDTO> rewardList;


}
