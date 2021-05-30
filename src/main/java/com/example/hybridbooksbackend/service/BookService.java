package com.example.hybridbooksbackend.service;

import com.example.hybridbooksbackend.model.Book;

import java.util.List;

public interface BookService {
    Book create(Book book);
    List<Book> getAll();
    Book get(Long id);
    Book update(Book book);
    void delete(Long id);
}
