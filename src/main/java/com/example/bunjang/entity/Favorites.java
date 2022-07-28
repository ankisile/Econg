package com.example.bunjang.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Favorites") //table 이름
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Favorites extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
