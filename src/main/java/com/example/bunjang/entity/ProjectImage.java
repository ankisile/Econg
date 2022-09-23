package com.example.bunjang.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ProjectImage") //table 이름
@ToString(exclude = "project")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectImage extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
