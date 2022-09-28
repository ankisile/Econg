package com.example.bunjang.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@ToString(exclude = "project")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reward extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int price;

    private int stock;

    private int soldQuantity;

    private String combination;


    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;


    public void changeSoldQuantity(){
        this.soldQuantity = this.soldQuantity +1;
    }
}
