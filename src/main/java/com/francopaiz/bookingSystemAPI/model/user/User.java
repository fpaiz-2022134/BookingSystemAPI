package com.francopaiz.bookingSystemAPI.model.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Document(collection = "usuarios")
public class User{

    @Id
    private Long id;
    private String name;
    private String email;
    private String passwordHash;
    private List<RoleEnum> roles;

    public User() {

    }

    public User(String name, String email, String passwordHash) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        roles = new ArrayList<>(Collections.singleton(RoleEnum.USER));
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public List<RoleEnum> getRoles() {
        return roles;
    }

    public void addRole(RoleEnum role) {
        if (!roles.contains(role)) {
            roles.add(role);
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setRoles(List<RoleEnum> roles) {
        this.roles = roles;
    }

}
