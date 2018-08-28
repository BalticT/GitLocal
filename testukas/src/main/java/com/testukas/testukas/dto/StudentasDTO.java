package com.testukas.testukas.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testukas.testukas.entity.Pazymiai;
import com.testukas.testukas.entity.Studentas;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


public class StudentasDTO {

    private Long id;

    private String vardas;

    private String pavarde;

    private String el_pastas;


    public StudentasDTO() {

    }



    public StudentasDTO(Studentas entity) {

        setId(entity.getStudentId());

        setVardas(entity.getVardas());

        setPavarde(entity.getPavarde());

        setEl_pastas(entity.getEl_pastas());

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVardas() {
        return vardas;
    }

    public void setVardas(String vardas) {
        this.vardas = vardas;
    }

    public String getPavarde() {
        return pavarde;
    }

    public void setPavarde(String pavarde) {
        this.pavarde = pavarde;
    }

    public String getEl_pastas() {
        return el_pastas;
    }

    public void setEl_pastas(String el_pastas) {
        this.el_pastas = el_pastas;
    }
}
