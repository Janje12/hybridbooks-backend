package com.example.hybridbooksbackend.service;

import com.example.hybridbooksbackend.model.BookEntity;

import java.util.List;

public interface BookService {
    BookEntity createBook(BookEntity book) throws Exception;
    List<BookEntity> getAllBooks();
    BookEntity getBook(String type, String value) throws Exception;
    BookEntity updateBook(BookEntity book) throws Exception;
    BookEntity deleteBook(String type, String value) throws Exception;
}
