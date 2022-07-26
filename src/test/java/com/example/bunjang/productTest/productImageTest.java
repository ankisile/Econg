package com.example.bunjang.productTest;

import com.example.bunjang.dto.ProductDTO;
import com.example.bunjang.entity.Product;
import com.example.bunjang.entity.ProductImage;
import com.example.bunjang.repository.ProductImageRepository;
import com.example.bunjang.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class productImageTest {

    @Autowired
    private ProductImageRepository productImageRepository;

    @Test
    public void insertProduct(){
        Product product = Product.builder().productId(1L).build();
        ProductImage productImage = ProductImage.builder()
                .productImgUrl("Url....")
                .represent(true)
                .product(product)
                .build();

        productImageRepository.save(productImage);
    }

    @Test
    public void insertProduct2(){
        Product product = Product.builder().productId(1L).build();
        ProductImage productImage = ProductImage.builder()
                .productImgUrl("Url....")
                .represent(false)
                .product(product)
                .build();

        productImageRepository.save(productImage);
    }

    @Test
    public void getImages(){

        List<ProductImage> result = productImageRepository.findByProduct_ProductId(1L);

       List<String> str= result.stream().map(arr -> {
            return arr.getProductImgUrl();
        }).collect(Collectors.toList());

        for(String s : str){
            System.out.println(s);
        }
    }

}
