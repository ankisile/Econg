package com.example.bunjang.service;

import com.example.bunjang.dto.LikeReqDTO;
import com.example.bunjang.dto.ProductDTO;
import com.example.bunjang.dto.ProductDetailDTO;
import com.example.bunjang.dto.ProductReqDTO;
import com.example.bunjang.entity.Favorites;
import com.example.bunjang.entity.Product;
import com.example.bunjang.entity.ProductImage;
import com.example.bunjang.entity.User;
import com.example.bunjang.repository.FavoritesRepository;
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
    private final FavoritesRepository favoritesRepository;
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
    public List<ProductDTO> getAllProducts() {

        List<Object[]> result = productRepository.getAllProductsWithImage();

        return result.stream().map(arr -> {
            Product product = (Product) arr[0];
            ProductImage productImage = (ProductImage) arr[1];
            return new ProductDTO(product.getProductId(),product.getTitle(), productImage.getProductImgUrl(),product.getUser().getUserName(),  product.getPrice());
        }).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<ProductDTO> getCrowdProducts() {

        List<Object[]> result = productRepository.getCrowdProductsWithImage();

        return result.stream().map(arr -> {
            Product product = (Product) arr[0];
            ProductImage productImage = (ProductImage) arr[1];
            return new ProductDTO(product.getProductId(), product.getTitle(), productImage.getProductImgUrl(),product.getUser().getUserName(),  product.getPrice());
        }).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<ProductDTO> getProducts() {
        List<Object[]> result = productRepository.getProductsWithImage();

        return result.stream().map(arr -> {
            Product product = (Product) arr[0];
            ProductImage productImage = (ProductImage) arr[1];
            return new ProductDTO(product.getProductId(),product.getTitle(), productImage.getProductImgUrl(),product.getUser().getUserName(),  product.getPrice());
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

//        List<ProductImage> imgResult =productImageRepository.findByProduct_ProductId(id);
//        List<String> str= imgResult.stream().map(arr -> {
//            return arr.getProductImgUrl();
//        }).collect(Collectors.toList());

        ProductImage imgResult = productImageRepository.findByProduct_ProductIdAndAndRepresent(id, true);

        return new ProductDetailDTO(product.getProductId(), product.getTitle(), product.getPrice(), product.getExplanation(), product.getUser().getUserName(),product.getDeadline(),imgResult.getProductImgUrl());

    }

    @Transactional
    @Override
    public List<ProductDTO> getCompanyProductsWithImage(Long id) {
        List<Object[]> result = productRepository.getCompanyProductsWithImage(id);

        return result.stream().map(arr -> {
            Product product = (Product) arr[0];
            ProductImage productImage = (ProductImage) arr[1];
            return new ProductDTO(product.getProductId(), product.getTitle(), productImage.getProductImgUrl(),product.getUser().getUserName(),  product.getPrice());
        }).collect(Collectors.toList());
    }

    @Override
    public String pushLikes(Long userId, Long productId) {
        if(productRepository.findById(productId).isEmpty()){
            throw new RuntimeException("존재하지 않는 상품입니다");
        }
        Optional<Favorites> result = favoritesRepository.findByUser_UserIdAndProduct_ProductId(userId, productId);

        if(result.isPresent()){
            favoritesRepository.delete(result.get());
            return "찜 해제";
        }
        else{
            User user = User.builder().userId(userId).build();
            Product product = Product.builder().productId(productId).build();
            Favorites favorites = Favorites.builder().user(user).product(product).build();
            favoritesRepository.save(favorites);
            return "찜 등록";
        }

    }

}