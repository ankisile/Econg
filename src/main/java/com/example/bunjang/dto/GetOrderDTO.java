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
@RequiredArgsConstructor
public class GetOrderDTO {

    @NonNull
    private Long projectId;

    @NonNull
    private String title;

    @NonNull
    private String thumbnail;

    @NonNull
    private Long rewardId;

    @NonNull
    private String rewardName;

    @NonNull
    private int price;

    @NonNull
    private String combination;

    private Long orderId;

    private String status;
}
