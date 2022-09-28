package com.example.bunjang.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@DynamicInsert
@Entity
@Table(name = "Project") //table 이름
@ToString(exclude = "user")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Project extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 20)
    private String title;

    private LocalDate openingDate;

    private LocalDate closingDate;

    private int goalAmount;

    private int totalAmount;

    private int achievedRate;

    private String summary;

    private String content;

    private String thumbnail;

    private String status;

    private Boolean authenticate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void changeTotalAmount(int price){
        this.totalAmount = this.totalAmount+price;
    }

    public void changeAchievedRate(){
        this.achievedRate = (int)((double) this.totalAmount / (double) this.goalAmount * 100);
    }

}
