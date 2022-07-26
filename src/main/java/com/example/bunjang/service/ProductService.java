package com.example.bunjang.service;

import com.example.bunjang.dto.ProductDTO;
import com.example.bunjang.dto.ProductDetailDTO;
import com.example.bunjang.dto.ProductReqDTO;
import com.example.bunjang.dto.RegisterReqDTO;

import java.util.List;

public interface ProductService {

    void register(ProductReqDTO productReqDTO);

    List<ProductDTO> getProducts();

    ProductDetailDTO getProductDetail(Long id);
}
