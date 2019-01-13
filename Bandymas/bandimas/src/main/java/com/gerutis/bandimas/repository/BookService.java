package com.gerutis.bandimas.repository;

import com.gerutis.bandimas.entity.Book;
import com.gerutis.bandimas.entity.BooksCreationDto;
import org.springframework.data.repository.CrudRepository;

public interface BookService extends CrudRepository<Book, Long> {


}
