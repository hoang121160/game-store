package com.example.GameStore.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String passWord;

    @Column(nullable = false)
    private String email;

    private String address;

    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role roles;


}
