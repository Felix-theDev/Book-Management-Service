package com.felix.Book.Management.Service.service;

import com.felix.Book.Management.Service.model.Book;
import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> getAllBooks();
    Optional<Book> getBookById(Long id);
    Book addBook(Book book);
    Book updateBook(Long id, Book book);
    boolean deleteBook(Long id);


    void deleteAll();
}