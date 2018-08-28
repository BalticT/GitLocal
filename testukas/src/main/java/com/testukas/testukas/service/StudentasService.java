package com.testukas.testukas.service;

import com.testukas.testukas.dto.PazymiaiDTO;
import com.testukas.testukas.entity.Pazymiai;
import com.testukas.testukas.entity.Studentas;
import com.testukas.testukas.repository.PazymiaiRepository;
import com.testukas.testukas.repository.StudentasRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentasService {

    @Autowired
    private PazymiaiRepository  pazymiaiRepository;

    @Autowired
    private StudentasRepository repository;


    public int uzkraukVidurki () {

        List<Pazymiai> vidur = (List<Pazymiai>) pazymiaiRepository.findAll();



int vidurkis = 5;

        return vidurkis;
    }



        public List<Studentas> uzkrautiDuomenys () {
        List<Studentas> listas = (List<Studentas>) repository.findAll();


            Collections.sort(listas, new CustomComparator());

        return listas;
    }



}
