package com.felix.Book.Management.Service.controller;

import com.felix.Book.Management.Service.exception.BookNotFoundException;
import com.felix.Book.Management.Service.model.Book;
import com.felix.Book.Management.Service.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/getAllBooks")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> books = bookService.getAllBooks();

            return ResponseEntity.ok(books);
    }

    @GetMapping("/getABook/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        try {
            Optional<Book> book = bookService.getBookById(id);
            if (book.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(book.get());
        } catch (BookNotFoundException exception) {
            throw exception;
        }
    }


    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book createdBook = bookService.addBook(book);

        return ResponseEntity.status(201).body(createdBook);
    }

    @PutMapping("/updateBook/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book){
        try {
            Book updatedBook = bookService.updateBook(id, book);
            return ResponseEntity.ok(updatedBook);
        } catch (BookNotFoundException exception) {
            throw exception;
        }
    }

    @DeleteMapping("deleteBook/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){

        try {
            boolean deleted = bookService.deleteBook(id);
            if (deleted) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        } catch (BookNotFoundException exception) {
            throw exception;
        }
    }

    @DeleteMapping("/clearDatabase")
    public ResponseEntity<Void> clearDatabase() {
        bookService.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
