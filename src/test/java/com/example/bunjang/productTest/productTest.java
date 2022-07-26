package com.example.bunjang.productTest;

import com.example.bunjang.entity.Product;
import com.example.bunjang.entity.User;
import com.example.bunjang.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class productTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void insertProduct(){
        Product product = Product.builder()
                .title("사과")
                .category("온라인 쇼핑")
                .explanation("사과입니다")
                .price(20000)
                .build();

        productRepository.save(product);
    }
    @Test
    public void getProductsWithImage(){
        List<Object[]> result = productRepository.getProductsWithImage();
        System.out.println("======luull======");
        System.out.println(result);
        for(Object[] arr : result) {
            System.out.println("======testttt======");
            System.out.println(Arrays.toString(arr));
        }
    }

    @Test
    public void getProductDetail(){
//        List<Object[]> result = productRepository.getProductDetail(1l);
//        System.out.println("======luull======");
//
//        for(Object[] arr : result) {
//            System.out.println("======testttt======");
//            System.out.println(Arrays.toString(arr));
//            Product p = (Product) arr[0];
//            System.out.println(p.getTitle());
//        }

        Optional<Product> result = productRepository.findById(1L);

        System.out.println(result);
    }
}
