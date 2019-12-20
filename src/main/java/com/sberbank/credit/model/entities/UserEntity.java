package com.sberbank.credit.model.entities;

import com.sberbank.credit.config.constants.CreditConstants;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

@Entity
@Table(schema = CreditConstants.CREDIT, name = "user")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fio")
    private String fio;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    public UserEntity() {
    }

    public UserEntity(String fio, @Size(max = 50) @NotNull @NotEmpty(message = "User login must not be empty!") String login, @NotNull @NotEmpty(message = "User password cannot be empty!") String password) {
        this.fio = fio;
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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