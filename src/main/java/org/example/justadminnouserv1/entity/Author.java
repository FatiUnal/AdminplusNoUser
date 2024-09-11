package org.example.justadminnouserv1.entity;

import jakarta.persistence.Entity;

import java.util.Set;

@Entity
public class Author extends User{
    private String biography;

    public Author(String name, String username, String password, Set<Role> authorities, String biography) {
        super(name, username, password, authorities);
        this.biography = biography;
    }

    public Author() {
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
