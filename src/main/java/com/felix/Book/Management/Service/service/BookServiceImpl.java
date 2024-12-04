package com.felix.Book.Management.Service.service;

import com.felix.Book.Management.Service.exception.BookNotFoundException;
import com.felix.Book.Management.Service.model.Book;
import com.felix.Book.Management.Service.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            throw new BookNotFoundException("Book with ID " + id + " not found.");
        }
        return book;

    }

    public Book addBook(Book book) {
        return bookRepository.save(book);

    }

    public Book updateBook(Long id, Book book) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book with ID " + id + " not found.");
        }

        book.setId(id);
        return bookRepository.save(book);
    }

    public boolean deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book with ID " + id + " not found.");
        }

        bookRepository.deleteById(id);
        return true;
    }

    @Override
    public void deleteAll() {
        bookRepository.deleteAll();
    }
}
