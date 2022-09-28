package com.example.bunjang.dto;

import lombok.*;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostOrderDTO {

    private Long rewardId;

    private String rewardName;

    private int price;

    private String deliveryAddress;

}
