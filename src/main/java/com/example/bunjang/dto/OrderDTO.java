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

    private String orderName;

    private String orderStatus;

    private String deliveryAddress;

    private String paymentMethodType;


}
