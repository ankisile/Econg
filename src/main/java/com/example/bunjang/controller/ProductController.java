package com.example.bunjang.controller;

import com.example.bunjang.dto.LikeReqDTO;
import com.example.bunjang.dto.ProductDTO;
import com.example.bunjang.dto.ProductDetailDTO;
import com.example.bunjang.service.ProductService;
import com.example.bunjang.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Log4j2
@RequestMapping("/app/products")
public class ProductController {

    private final ProductService productService;
    private final UserService userService;
    //Get products
    @GetMapping()
    public ResponseEntity<List<ProductDTO>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDetailDTO> getProductDetail(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.getProductDetail(productId));
    }

    @PostMapping("/likes")
    public ResponseEntity<String> pushLikes(@RequestBody LikeReqDTO likeDTO){
        Long userId = userService.findUserId();
        Long productId = likeDTO.getProductId();
        return ResponseEntity.ok(productService.pushLikes(userId, productId));

    }
}
