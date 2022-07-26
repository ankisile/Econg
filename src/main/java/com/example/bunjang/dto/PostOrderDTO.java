package com.example.bunjang.dto;

import lombok.*;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostOrderDTO {

    private Long projectId;

    private Long rewardId;

    private String rewardName;

    private int price;

    private String deliveryAddress;

}
