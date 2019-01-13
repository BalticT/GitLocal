package com.gerutis.bandimas.entity;



import javax.persistence.*;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name="image_model")
public class ImageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Lob
    @Column(name="pic")
    private byte[] pic;

    public ImageModel(){}

    public ImageModel(long id, String name, String type, byte[] pic){
        this.id = id;
        this.name = name;
        this.type = type;
        this.pic = pic;
    }



    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "image_tag",
            joinColumns = @JoinColumn(name = "image_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )

    private Set<Tag> tags =  new HashSet<>();

    //Getters and setters ommitted for brevity


    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public void addTag(Tag tag) {
        tags.add(tag);
        tag.getImages().add(this);
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
        tag.getImages().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImageModel)) return false;
        return id != null && id.equals(((ImageModel) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }




//
//    @JsonIgnore
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "image_tags", joinColumns = { @JoinColumn(name = "id") }, inverseJoinColumns = { @JoinColumn(name = "tag_id") })
//
//    private Set<Tag> tags = new HashSet<Tag>();



    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getType(){
        return this.type;
    }

    public void setType(String type){
        this.type = type;
    }

    public byte[] getPic(){
        return this.pic;
    }

    public void setPic(byte[] pic){
        this.pic = pic;
    }


//    public void setAll(String name, String type ) {
//        this.name = name;
//        this.type = type;
//    }




 //    public Set<Tag> getTags() {
//        return tags;
//    }
//
//    public void setTags(Set<Tag> tags) {
//        this.tags = tags;
//    }
//

//
//    final Long postId = doInJPA(entityManager -> {
//        ImageModel post1 = new ImageModel( );
//        ImageModel post2 = new ImageModel( );
//
//        Tag tag1 = new Tag("Java");
//        Tag tag2 = new Tag("Hibernate");
//
//        post1.addTag(tag1);
//        post1.addTag(tag2);
//
//        post2.addTag(tag1);
//
//        entityManager.persist(post1);
//        entityManager.persist(post2);
//
//        return post1.id;
//    });

    String[] multiCheckboxSelectedValues;

    public String[] getMultiCheckboxSelectedValues() {
        return multiCheckboxSelectedValues;
    }

    public void setMultiCheckboxSelectedValues(String[] multiCheckboxSelectedValues) {
        this.multiCheckboxSelectedValues = multiCheckboxSelectedValues;
    }

}
