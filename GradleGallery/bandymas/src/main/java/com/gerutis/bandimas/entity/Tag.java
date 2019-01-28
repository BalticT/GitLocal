package com.gerutis.bandimas.entity;


import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.*;


@Entity(name = "Tag")
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tag_id;

    @NaturalId
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<ImageModel> images =  new HashSet<>();

    public Tag() {}

    public Tag(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(name, tag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public Long getTag_id() {
        return tag_id;
    }

    public void setTag_id(Long tag_id) {
        this.tag_id = tag_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ImageModel> getImages() {
        return images;
    }

    public void setImages(Set<ImageModel> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + tag_id +
                ", name='" + name + '\'' +
                ", images=" + images +
                '}';
    }
}



