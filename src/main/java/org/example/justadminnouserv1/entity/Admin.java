package org.example.justadminnouserv1.entity;

import jakarta.persistence.Entity;

import java.util.Set;

@Entity
public class Admin extends User{
    public Admin(String name, String username, String password, Set<Role> authorities) {
        super(name, username, password, authorities);
    }
    public Admin(){};
}
