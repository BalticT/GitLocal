package com.gerutis.bandimas.controller;

import com.gerutis.bandimas.entity.Catalog;
import com.gerutis.bandimas.entity.Movie;
import com.gerutis.bandimas.repository.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class MyTestController {


    @Autowired
    BookService bookService;


    @ModelAttribute("catalog")
    public Catalog getCatalog() {
        Catalog catalog = new Catalog();
        catalog.setMovies(new ArrayList<>());


        return catalog;
    }

    @GetMapping("/addcatalog")
    public String addCatalogForm(@ModelAttribute("catalog") Catalog catalog) {

//        for (int i = 1; i <= 5; i++) {
//            catalog.getMovies().add(new Movie());
//
//        }
        return "addCatalogForm";
    }

    @PostMapping("/savecatalog")
    public String addCatalog(@ModelAttribute("catalog") Catalog catalog) {
        //some logic

        return "redirect:/books/all";
    }
}
