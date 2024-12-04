package com.felix.Book.Management.Service;


import com.felix.Book.Management.Service.exception.BookNotFoundException;
import com.felix.Book.Management.Service.model.Book;
import com.felix.Book.Management.Service.model.BookType;
import com.felix.Book.Management.Service.repository.BookRepository;
import com.felix.Book.Management.Service.service.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBooks() {
        // Arrange
        Book book1 = new Book(1L, "Book 1", "123456", LocalDate.now(), BigDecimal.valueOf(100.0), BookType.HARDCOVER);
        Book book2 = new Book(2L, "Book 2", "654321", LocalDate.now(), BigDecimal.valueOf(200.0), BookType.SOFTCOVER);
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        // Act
        List<Book> books = bookService.getAllBooks();

        // Assert
        assertNotNull(books);
        assertEquals(2, books.size());
        verify(bookRepository, times(1)).findAll();
    }
    @Test
    void testGetBookById_BookExists() {
        // Arrange
        Book book = new Book(1L, "Book 1", "123456", LocalDate.now(), BigDecimal.valueOf(100.0), BookType.HARDCOVER);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        // Act
        Optional<Book> result = bookService.getBookById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Book 1", result.get().getName());
        verify(bookRepository, times(1)).findById(1L);
    }
    @Test
    void testGetBookById_BookDoesNotExist() {
        // Arrange
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(BookNotFoundException.class, () -> bookService.getBookById(1L));
        assertEquals("Book with ID 1 not found.", exception.getMessage());
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void testAddBook() {
        // Arrange
        Book book = new Book(null, "New Book", "123456", LocalDate.now(), BigDecimal.valueOf(150.0), BookType.HARDCOVER);
        when(bookRepository.save(book)).thenReturn(book);

        // Act
        Book savedBook = bookService.addBook(book);

        // Assert
        assertNotNull(savedBook);
        assertEquals("New Book", savedBook.getName());
        verify(bookRepository, times(1)).save(book);
    }
}
