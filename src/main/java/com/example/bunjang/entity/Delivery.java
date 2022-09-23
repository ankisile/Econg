package com.example.bunjang.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@ToString(exclude = "user")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Delivery extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String phone;

    private boolean checked;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
