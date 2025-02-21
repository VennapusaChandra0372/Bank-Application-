package com.chandra.Authentication_Microservice.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class User {

    @Id
    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="USER_ROLE",
    joinColumns = {
            @JoinColumn(name="USER_ID")
    },
    inverseJoinColumns = {
            @JoinColumn(name="ROLE_ID")
    }
    )
    private Set<Role> role;


    public User(){

    }

    public User( String username, String password, Set<Role> role) {

        this.username = username;
        this.password = password;
        this.role = role;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +

                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
