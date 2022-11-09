package com.example.bunjang.dto;

import lombok.*;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private String tid;

    private Long projectId;

    private String projectName;

    private String orderName;

    private String orderStatus;

    private String deliveryAddress;

    private String paymentMethodType;

    private int totalMoney;

    private String rewardName;

    private String combination;
}
