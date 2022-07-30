package com.example.bunjang.controller;

import com.example.bunjang.dto.CompanyDTO;
import com.example.bunjang.dto.CompanyDetailDTO;
import com.example.bunjang.dto.ProductDTO;
import com.example.bunjang.dto.ProductDetailDTO;
import com.example.bunjang.service.ProductService;
import com.example.bunjang.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Log4j2
@RequestMapping("/app/companies")
public class CompanyController {


    private final UserService userService;
    private final ProductService productService;

    @GetMapping("")
    public ResponseEntity<List<CompanyDTO>> getCompanies() {
        return ResponseEntity.ok(userService.getCompanies());
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyDetailDTO> getCompanyDetail(@PathVariable Long companyId) {

        String [] result = userService.getCompanyDetail(companyId);

        List<ProductDTO> productList = productService.getCompanyProductsWithImage(companyId);


        return ResponseEntity.ok(new CompanyDetailDTO(result[0], result[1], productList));
    }
}
