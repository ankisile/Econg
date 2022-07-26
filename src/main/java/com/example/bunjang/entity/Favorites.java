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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

}
