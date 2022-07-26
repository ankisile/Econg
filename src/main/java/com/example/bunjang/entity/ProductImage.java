package com.example.bunjang.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ProductImage") //table 이름
@ToString(exclude = "product")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductImage extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productImageId;

    private String productImgUrl;

    private boolean represent;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
