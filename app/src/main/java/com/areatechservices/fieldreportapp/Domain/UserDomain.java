package com.areatechservices.fieldreportapp.Domain;

/**
 * Created by djbabs on 12/7/18.
 */

public class UserDomain {

    private Long id;

    private String name;
    private String email;

    public UserDomain(Long id, String name, String email){

        this.setId(id);
        this.setName(name);
        this.setEmail(email);

    }
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
