package com.example.bunjang.service;

import com.example.bunjang.dto.ProductDTO;
import com.example.bunjang.dto.ProductDetailDTO;
import com.example.bunjang.dto.ProductReqDTO;
import com.example.bunjang.entity.Product;
import com.example.bunjang.entity.ProductImage;
import com.example.bunjang.entity.User;
import com.example.bunjang.repository.ProductImageRepository;
import com.example.bunjang.repository.ProductRepository;
import com.example.bunjang.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public void register(ProductReqDTO productReqDTO) {
        Product product = Product.builder()
                .title(productReqDTO.getTitle())
                .category(productReqDTO.getCategory())
                .explanation(productReqDTO.getExplanation())
                .price(productReqDTO.getPrice())
                .build();

        productRepository.save(product);
    }

    @Transactional
    @Override
    public List<ProductDTO> getProducts() {

        List<Object[]> result = productRepository.getProductsWithImage();


        return result.stream().map(arr -> {
            return new ProductDTO((String) arr[0],(String) arr[1]);
        }).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ProductDetailDTO getProductDetail(Long id) {

        Optional<Product> result = productRepository.findById(id);
        if(result.isEmpty()){
            throw new RuntimeException("존재하지 않은 id 입니다.");

        }
        Product product = result.get();

        List<ProductImage> imgResult =productImageRepository.findByProduct_ProductId(id);
        List<String> str= imgResult.stream().map(arr -> {
            return arr.getProductImgUrl();
        }).collect(Collectors.toList());

        return new ProductDetailDTO(product.getTitle(), product.getPrice(), product.getExplanation(), product.getUser().getUserName(),str);

//        List<Object[]> result = productRepository.getProductDetail(id);
//
//        return result.stream().map(arr->{
//            Product p = (Product) arr[0];
//
//            return new ProductDetailDTO(p.getTitle(),)
//        })
//        return null;
    }

}