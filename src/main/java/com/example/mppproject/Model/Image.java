package com.example.mppproject.Model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "image")
public class Image {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "url")
    private String url;

    @Column(name = "extension")
    private String extension;

    @Column(name = "caption")
    private String caption;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "property_id")
    private Property property;

    public Image(){}

    public Image(String url, String extension, String caption) {
        this.url = url;
        this.extension = extension;
        this.caption = caption;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}