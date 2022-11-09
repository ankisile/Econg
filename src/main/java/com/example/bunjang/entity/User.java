package com.example.bunjang.entity;

//import com.example.bunjang.config.Salt;
import com.example.bunjang.common.Role;
import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "User") //table 이름
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, length = 30)
    private String email;

    @Column(nullable = false, length = 200)
    private String password;

    @Column(name = "phone", nullable = false, length = 13)
    private String phone;

    @Column(name = "name", nullable = false, length = 10)
    private String name;

    @Column(name = "nickName", nullable = false, length = 10)
    private String nickName;

    @Column(length = 200)
    private String description;

    @Column(length = 200)
    private String profileUrl;

    @Column(name = "activated")
    private boolean activated;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean authenticate;

    public void modifyNickName(String nickName){
        this.nickName = nickName;
    }
    public void modifyDescription(String description){
        this.description = description;
    }
    public void modifyProfileUrl(String profileUrl){
        this.profileUrl = profileUrl;
    }

    @Builder
    public User(String email, String password,  Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }



//    @ManyToMany
//    @JoinTable(
//            name = "user_authority",
//            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
//            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
//    private Set<Authority> authorities;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "salt_id")
//    private Salt salt;

}
