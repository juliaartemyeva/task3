package org.user.model;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "country")
    private String country;
    @Column(name = "role")
    private String role;

    public User() {
    }

    public User(String login, String password, String country, String role) {
        super();
        this.login = login;
        this.password = password;
        this.country = country;
        this.role = role;
    }
    public User(int id, String login, String password, String country) {
        super();
        this.id = id;
        this.login = login;
        this.password = password;
        this.country = country;
    }

    public User(int id, String login, String password, String country, String role) {
        super();
        this.id = id;
        this.login = login;
        this.password = password;
        this.country = country;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String name) {
        this.login = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String email) {
        this.password = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
