package com.example.bunjang.productTest;

import com.example.bunjang.common.ProductType;
import com.example.bunjang.entity.Product;
import com.example.bunjang.entity.User;
import com.example.bunjang.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class productTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void insertProduct(){
        User user = User.builder().userId(5L).build();

        Product product = Product.builder()
                .title("에코팜의 수세미")
                .category("온라인 쇼핑")
                .explanation("Ecfarm Sell")
                .price(3500)
                .productType(ProductType.SELLPRODUCT)
                .user(user)
                .build();

        productRepository.save(product);
    }

//    @Test
//    public void insertCrowdProduct(){
//        User user = User.builder().userId(5L).build();
//
//        Product product = Product.builder()
//                .title("EcoFarm's Crowd Product")
//                .category("온라인 쇼핑")
//                .explanation("Eco2 Crowd")
//                .price(20000)
//                .deadline(LocalDate.of(2022,8,24))
//                .productType(ProductType.CROWD)
//                .user(user)
//                .build();
//
//        productRepository.save(product);
//    }
//
//    @Test
//    public void getProductsWithImage(){
//        List<Object[]> result = productRepository.getCrowdProductsWithImage();
//        System.out.println("======luull======");
//        System.out.println(result);
//        for(Object[] arr : result) {
//            System.out.println("======testttt======");
//            System.out.println(Arrays.toString(arr));
//        }
//    }
//
//    @Test
//    public void getProductDetail(){
////        List<Object[]> result = productRepository.getProductDetail(1l);
////        System.out.println("======luull======");
////
////        for(Object[] arr : result) {
////            System.out.println("======testttt======");
////            System.out.println(Arrays.toString(arr));
////            Product p = (Product) arr[0];
////            System.out.println(p.getTitle());
////        }
//
//        Optional<Product> result = productRepository.findById(1L);
//
//        System.out.println(result);
//    }
}
