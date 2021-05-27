package com.example.hybridbooksbackend.controller;

import com.example.hybridbooksbackend.model.BookEntity;
import com.example.hybridbooksbackend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/book", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookEntity>> getAllBooks() {
        List<BookEntity> list = bookService.getAllBooks();
        return new ResponseEntity<List<BookEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{type}/{value}")
    public ResponseEntity<BookEntity> getBook(@PathVariable("type") String type, @PathVariable("value") String value)
            throws Exception {
        BookEntity entity = bookService.getBook(type, value);
        return new ResponseEntity<BookEntity>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookEntity> createBook(@RequestBody BookEntity Book) throws Exception {
        BookEntity created = bookService.createBook(Book);
        return new ResponseEntity<BookEntity>(created, new HttpHeaders(), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<BookEntity> updateBook(BookEntity Book)
            throws Exception {
        BookEntity updated = bookService.updateBook(Book);
        return new ResponseEntity<BookEntity>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{type}/{value}")
    public ResponseEntity<BookEntity> deleteBook(@PathVariable("type") String type, @PathVariable("value") String value)
            throws Exception {
        BookEntity deleted = bookService.deleteBook(type, value);
        return new ResponseEntity<BookEntity>(deleted, new HttpHeaders(), HttpStatus.OK);
    }
}
