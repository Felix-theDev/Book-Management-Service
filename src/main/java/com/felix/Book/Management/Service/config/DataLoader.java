package com.felix.Book.Management.Service.config;

import com.felix.Book.Management.Service.model.Book;
import com.felix.Book.Management.Service.model.BookType;
import com.felix.Book.Management.Service.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;

@Configuration
@RequiredArgsConstructor
public class DataLoader {

    private final BookService bookService;

    @Bean
    public CommandLineRunner loadData() {
        return args -> {
            Book book1 = new Book();
            book1.setName("PayU Solutions");
            book1.setIsbn("978013491");
            book1.setPublishDate(LocalDate.of(2018, 1, 1));
            book1.setPrice(new BigDecimal("450"));
            book1.setBookType(BookType.HARDCOVER);

            Book book2 = new Book();
            book2.setName("Java How to Program");
            book2.setIsbn("95792991");
            book2.setPublishDate(LocalDate.of(2008, 8, 1));
            book2.setPrice(new BigDecimal("300"));
            book2.setBookType(BookType.SOFTCOVER);

            bookService.addBook(book1);
            bookService.addBook(book2);
        };
    }

}
