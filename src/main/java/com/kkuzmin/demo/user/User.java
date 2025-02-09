package com.kkuzmin.demo.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password1;

    @Column(name = "role")
    private String role;

    public User() {}

    public User(String username, String password, String role) {
        this.username = username;
        this.password1 = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password1;
    }

    public void setPassword(String password) {
        this.password1 = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
