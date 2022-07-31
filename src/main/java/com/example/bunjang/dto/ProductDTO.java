package com.example.bunjang.dto;

import lombok.*;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String title;
    private String imgUrl;
    private String companyName;
    private int price;
}
