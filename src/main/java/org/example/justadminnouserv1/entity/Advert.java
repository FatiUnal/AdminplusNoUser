package org.example.justadminnouserv1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
public class Advert {
    @Id
    @GeneratedValue
    private long id;
    private String advertName;
    private String image;
    private String link;
    private LocalDateTime localDateTime;

    public Advert(String advertName, String image, String link) {
        this.advertName = advertName;
        this.image = image;
        this.link = link;
        this.localDateTime= LocalDateTime.now();
    }
    public Advert(){
        this.localDateTime= LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAdvertName() {
        return advertName;
    }

    public void setAdvertName(String advertName) {
        this.advertName = advertName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public String getImage() {
        return image;
    }
}
