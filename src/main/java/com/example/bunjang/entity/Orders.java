package com.example.bunjang.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Orders extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderName;

    private int donation;

    private String orderStatus;

    private String deliveryAddress;

    private String paymentMethodType;

    private String paymentTid;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;


    @ManyToOne
    @JoinColumn(name = "reward_id")
    private Reward reward;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
