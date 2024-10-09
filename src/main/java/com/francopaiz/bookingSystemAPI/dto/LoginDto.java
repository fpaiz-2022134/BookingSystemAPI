package com.francopaiz.bookingSystemAPI.dto;

public class LoginDto {

    private String email;
    private String password;
    private String idUser;

    public LoginDto() {
    }

    public LoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return idUser;
    }

    public void setId(String id) {
        this.idUser = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
