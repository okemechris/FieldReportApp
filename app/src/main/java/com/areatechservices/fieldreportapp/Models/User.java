package com.areatechservices.fieldreportapp.Models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by djbabs on 12/5/18.
 */
@Entity
public class User {

    @NonNull
    @PrimaryKey
    private Long id;

    private String name;
    private String email;

    private int status;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
