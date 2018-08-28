package com.testukas.testukas.dto;

import com.testukas.testukas.entity.Pazymiai;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;


public class PazymiaiDTO {

    private Long id;

    private Long studentas_id;

    private LocalDate data;

    private Integer pazymys;

    public PazymiaiDTO() {

    }



    public PazymiaiDTO(Pazymiai entity) {

        setId(entity.getPazymiai_id());

        setStudentas_id(entity.getStudentas_id());

        setData(entity.getData());

        setPazymys(entity.getPazymys());

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

