package com.testukas.testukas.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity(name = "studentai")
public class Studentas  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Long studentId;

     String vardas;
     String pavarde;

    private String el_pastas;




    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "studentu_pazymiai", joinColumns = {@JoinColumn(name = "Id")}, inverseJoinColumns = {@JoinColumn(name = "studentas_id")})
    private List<Pazymiai> pazymiai = new ArrayList<Pazymiai>();


    public String getVardas() {
        return vardas;
    }

    public void setVardas(String vardas) {
        this.vardas = vardas;
    }

    public String getPavarde() {
        return pavarde;
    }

    public void setPavarde(String lastname) {
        this.pavarde = pavarde;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getEl_pastas() {
        return el_pastas;
    }

    public void setEl_pastas(String el_pastas) {
        this.el_pastas = el_pastas;
    }


    public List<Pazymiai> getPazymiai() {
        return pazymiai;
    }

    public void setPazymiai(List<Pazymiai> pazymiai) {
        this.pazymiai = pazymiai;
    }





    }
