package com.AuthLab.models;
import jakarta.persistence.*;

import java.util.List;


@Entity
public class HashUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String userName;
    String password;
    @OneToMany(mappedBy = "siteUser")
    List<Hashy> hashies;

    protected HashUser(){}

    public HashUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Hashy> getHashies() {
        return hashies;
    }

    public void setHashies(List<Hashy> hashies) {
        this.hashies = hashies;
    }

    @Override
    public String toString() {
        return "SiteUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getHashPass() {
        return null;
    }
}