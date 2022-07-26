package com.example.bunjang.dto;

import com.example.bunjang.entity.ProductImage;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailDTO {

    private String title;
    private int price;
    private String explanation;
    private String companyName;
    private List<String> productImages;
}
