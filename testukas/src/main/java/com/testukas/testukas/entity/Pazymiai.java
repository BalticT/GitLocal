package com.testukas.testukas.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity(name = "pazymiai")
public class Pazymiai {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pazymiai_id;

    private Long studentas_id;

    private LocalDate data;

    private Integer pazymys;

    public Long getPazymiai_id() {
        return pazymiai_id;
    }

    public void setPazymiai_id(Long pazymiai_id) {
        this.pazymiai_id = pazymiai_id;
    }

    public Long getStudentas_id() {
        return studentas_id;
    }

    public void setStudentas_id(Long studentas_id) {
        this.studentas_id = studentas_id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Integer getPazymys() {
        return pazymys;
    }

    public void setPazymys(Integer pazymys) {
        this.pazymys = pazymys;
    }
}

