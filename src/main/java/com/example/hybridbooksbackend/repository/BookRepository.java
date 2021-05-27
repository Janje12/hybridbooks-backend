package com.example.hybridbooksbackend.repository;

import com.example.hybridbooksbackend.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    Optional<BookEntity> findByTitle(String title);
    Optional<BookEntity> findByDescriptionContaining(String description);
    Optional<BookEntity> findByAuthor(String author);
    Optional<BookEntity> findByCoverImage(String coverImage);
    Optional<BookEntity> findByYearReleased(int yearReleased);
    Optional<BookEntity> findByCurrentAmount(int currentAmount);
    Optional<BookEntity> findByMaxAmount(int maxAmount);
}
