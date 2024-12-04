package com.felix.Book.Management.Service.repository;

import com.felix.Book.Management.Service.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
