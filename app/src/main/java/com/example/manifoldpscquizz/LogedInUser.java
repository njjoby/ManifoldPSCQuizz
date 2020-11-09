package com.example.manifoldpscquizz;

import java.io.Serializable;

public class LogedInUser implements Serializable {
    public User user;
    public String status;

    public LogedInUser() {
    }

    public LogedInUser(User user, String status) {
        this.user = user;
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
