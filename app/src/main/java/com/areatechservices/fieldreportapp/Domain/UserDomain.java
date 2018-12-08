package com.areatechservices.fieldreportapp.Domain;

/**
 * Created by djbabs on 12/7/18.
 */

public class UserDomain {

    private String name;

    public UserDomain(String name){

        this.setName(name);


    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
