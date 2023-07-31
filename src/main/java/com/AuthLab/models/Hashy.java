package com.AuthLab.models;
import jakarta.persistence.*;

@Entity
public class Hashy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String hashName;
    String hashContent;

    @ManyToOne
    HashUser hashUser;

    protected Hashy() {}

    public Hashy(String hashName, String hashContent, HashUser hashUser) {
        this.hashName = hashName;
        this.hashContent = hashContent;
        this.hashUser = hashUser;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHashName() {
        return hashName;
    }

    public void setHashName(String hashName) {
        this.hashName = hashName;
    }

    public String getHashContent() {
        return hashContent;
    }

    public void setHashContent(String hashContent) {
        this.hashContent = hashContent;
    }

    public HashUser getSiteUser() {
        return hashUser;
    }

    public void setSiteUser(HashUser hashUser) {
        this.hashUser = hashUser;
    }

    @Override
    public String toString() {
        return "Hashy{" +
                "id=" + id +
                ", hashName='" + hashName + '\'' +
                ", hashContent='" + hashContent + '\'' +
                ", siteUser=" + hashUser +
                '}';
    }
}

