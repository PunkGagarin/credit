package com.sberbank.credit.model.dtos;

import javax.validation.constraints.*;
import java.io.Serializable;

public class User implements Serializable {

    private String fio;

    @NotNull
    @NotEmpty(message = "User login cannot not be empty!")
    private String login;

    @NotNull
    @NotEmpty(message = "User password cannot be empty!")
    @Size(min = 6, message = "Minimum 6 symbols")
    private String password;

    public User() {
    }

    public User( String fio, String login, String password) {
        this.fio = fio;
        this.login = login;
        this.password = password;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
