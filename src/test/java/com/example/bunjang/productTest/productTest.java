package com.example.bunjang.productTest;

import com.example.bunjang.entity.Favorites;
import com.example.bunjang.entity.Project;
import com.example.bunjang.entity.User;
import com.example.bunjang.repository.FavoritesRepository;
import com.example.bunjang.repository.ProjectRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class productTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private FavoritesRepository favoritesRepository;

//    @Test
//    public void insertProduct(){
//        User user = User.builder().userId(5L).build();
//
//        Product product = Product.builder()
//                .title("에코팜의 수세미")
//                .category("온라인 쇼핑")
//                .explanation("Ecfarm Sell")
//                .price(3500)
//                .productType(ProductType.SELLPRODUCT)
//                .user(user)
//                .build();
//
//        productRepository.save(product);
//    }

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
//        List<Project> projects = projectRepository.findByStatusOrderByIdDesc("ONGOING");
//
//        String title = null;
//        for(Project project : projects) {
//            System.out.println("======testttt======");
//            System.out.println(project.getTitle());
//            title = project.getTitle();
//
//        }
//
//        Assertions.assertThat(title).isEqualTo("테스트");
//    }

    @Test
    void testLikes(){


        List<Favorites> str = favoritesRepository.findByUser_Id(2L);


    }

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
