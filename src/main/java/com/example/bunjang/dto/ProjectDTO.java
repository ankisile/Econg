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
public class ProjectDTO {

    private Long id;

    private String title;

    private LocalDate openingDate;

    private LocalDate closingDate;

    private int goalAmount;

    private int totalAmount;

    private int achievedRate;

    private String summary;

    private String content;

    private String thumbnail;

    private boolean projectAuthenticate;

    private boolean favorite;

    private String status;

    private Long userId;

    private String userName;

    private boolean userAuthenticate;

    private List<ProjectImgDTO> projectImgList;

    private List<RewardDTO> rewardList;

}
