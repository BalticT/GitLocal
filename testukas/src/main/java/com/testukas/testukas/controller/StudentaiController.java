package com.testukas.testukas.controller;

import com.testukas.testukas.entity.Studentas;
import com.testukas.testukas.service.StudentasService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/studentai")
public class StudentaiController {

    @Autowired
    private StudentasService service;

    @RequestMapping()
    public List<Studentas> getUserius() {

        return service.uzkrautiDuomenys();
    }

}

