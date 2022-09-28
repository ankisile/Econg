package com.example.bunjang.dto;

import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;

@Log4j2
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderDTO {

    private Long projectId;

    private String title;

    private String thumbnail;

    private Long rewardId;

    private String rewardName;

    private int price;

    private String combination;

}
