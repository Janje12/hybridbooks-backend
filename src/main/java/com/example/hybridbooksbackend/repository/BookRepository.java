package com.example.hybridbooksbackend.repository;

import com.example.hybridbooksbackend.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title);
    Optional<Book> findByDescriptionContaining(String description);
    Optional<Book> findByAuthor(String author);
    Optional<Book> findByCoverImage(String coverImage);
    Optional<Book> findByYearReleased(int yearReleased);
    Optional<Book> findByCurrentAmount(int currentAmount);
    Optional<Book> findByMaxAmount(int maxAmount);
}
