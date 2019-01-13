package com.gerutis.bandimas.entity;

import java.util.ArrayList;
import java.util.List;
import com.gerutis.bandimas.entity.Book;

public class BooksCreationDto {

    public Book book;

    private List<Book> books;

//    public BooksCreationDto() {}

    public BooksCreationDto(List<Book> books) {
        this.books = books;

//        this.books = new ArrayList<>();

        System.out.println(  " pirmas");
//        for (Book booke : books) {
//            if (( booke.getTitle().isEmpty() ) || (booke.getAuthor().isEmpty() )) {
////                books.remove(booke);
//                System.out.println("geras");
//            }
//        }
    }


    // default and parameterized constructor

    public void addBook(Book book) {
        this.books.add(book);


    }

    public List<Book> getBooks() {

//        if ((!book.getTitle().isEmpty() ) || (!book.getAuthor().isEmpty() )) {
//
//        }

        return books;
    }

    public void setBooks(List<Book> books) {

        this.books = books;
//        this.books = new ArrayList<>();
        System.out.println( " antras");


    }

    // getter and setter
}