package com.example.bunjang.productTest;

import com.example.bunjang.entity.Project;
import com.example.bunjang.repository.OrdersRepository;
import com.example.bunjang.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class productImageTest {

    @Autowired
    private OrdersRepository ordersRepository;

    @Test
    public void testRepository(){

        List<Project> list = ordersRepository.findProjectsByUserId(5L);

        for(Project p : list){
            System.out.println(p.getTitle());
        }

    }


//    @Test
//    public void insertProduct(){
//        Product product = Product.builder().productId(5L).build();
//        ProductImage productImage = ProductImage.builder()
//                .productImgUrl("gs://android-kotlin-firebase-debb2.appspot.com/images/bud.png")
//                .represent(true)
//                .product(product)
//                .build();
//
//        productImageRepository.save(productImage);
//    }

//    @Test
//    public void insertProduct2(){
//        Product product = Product.builder().productId(2L).build();
//        ProductImage productImage = ProductImage.builder()
//                .productImgUrl("Url not represent....")
//                .represent(false)
//                .product(product)
//                .build();
//
//        productImageRepository.save(productImage);
//    }
//
//    @Test
//    public void getImages(){
//
//        List<ProductImage> result = productImageRepository.findByProduct_ProductId(1L);
//
//       List<String> str= result.stream().map(arr -> {
//            return arr.getProductImgUrl();
//        }).collect(Collectors.toList());
//
//        for(String s : str){
//            System.out.println(s);
//        }
//    }

}
