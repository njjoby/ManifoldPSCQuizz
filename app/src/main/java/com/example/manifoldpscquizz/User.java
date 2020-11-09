package com.example.manifoldpscquizz;

import java.io.Serializable;

public class User implements Serializable {
    public String name;



    public String email;
    public String phone;
    public String password;
    public String status;// guest  // manifoldstudent

    public User(String name, String email, String phone, String password, String status) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.status = status;
    }

    public User() {
       // this.name = name;
    }
}
