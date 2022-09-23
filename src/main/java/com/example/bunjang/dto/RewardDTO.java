package com.example.bunjang.dto;

import lombok.*;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RewardDTO {

    private Long id;

    private String name;

    private int price;

    private int stock;

    private int soldQuantity;

    private String combination;
}
