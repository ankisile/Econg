package com.example.bunjang.entity;

//import com.example.bunjang.config.Salt;
import com.example.bunjang.common.Role;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    private Long userId;

    @Column(name = "userName", nullable = false, length = 10)
    private String userName;

    @Column(name = "email", nullable = false, length = 20)
    private String email;

    @Column(nullable = false, length = 200)
    private String password;

    @Column(name = "phone", nullable = false, length = 11)
    private String phone;

    @Column(length = 200)
    private String profileUrl;

    @Column(columnDefinition = "int default 0")
    private Integer point;

    @Column(name = "activated")
    private boolean activated;

    @Enumerated(EnumType.STRING)
    private Role role;

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
