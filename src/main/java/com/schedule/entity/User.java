package com.schedule.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@Table(name="user")
@Getter
@AllArgsConstructor
public class User extends CommonlyColumn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String email;


    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public User() {}

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
