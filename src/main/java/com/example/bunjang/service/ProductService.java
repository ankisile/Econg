package com.example.bunjang.service;

import com.example.bunjang.dto.*;

import java.util.List;

public interface ProductService {

    void register(ProductReqDTO productReqDTO);

    List<ProductDTO> getAllProducts();

    List<ProductDTO> getCrowdProducts();

    List<ProductDTO> getProducts();

    ProductDetailDTO getProductDetail(Long id);

    List<ProductDTO> getCompanyProductsWithImage(Long id);

    String pushLikes(Long userId, Long productId);
}
