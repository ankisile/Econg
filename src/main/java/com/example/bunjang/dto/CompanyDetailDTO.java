package com.example.bunjang.dto;

import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDetailDTO {
    private Long companyId;
    private String companyName;
    private String imgUrl;
    private List<ProductDTO> productList;
}
