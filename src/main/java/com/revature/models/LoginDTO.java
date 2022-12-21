package com.revature.models;

public class LoginDTO {

    private String ers_username;
    private String ers_password;

    public LoginDTO(String ers_username, String ers_password) {

        this.ers_username = ers_username;
        this.ers_password = ers_password;
    }

    public String getErs_username() {
        return ers_username;
    }

    public void setErs_username(String ers_username) {
        this.ers_username = ers_username;
    }

    public String getErs_password() {
        return ers_password;
    }

    public void setErs_password(String ers_password) {
        this.ers_password = ers_password;
    }
}
