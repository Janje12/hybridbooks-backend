package com.example.hybridbooksbackend.controller;

import com.example.hybridbooksbackend.dto.book.BookCreateDto;
import com.example.hybridbooksbackend.dto.book.BookUpdateDto;
import com.example.hybridbooksbackend.dto.book.BookViewDto;
import com.example.hybridbooksbackend.model.Book;
import com.example.hybridbooksbackend.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/books", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

    private final BookService bookService;
    private final ModelMapper modelMapper;

    public BookController(final BookService bookService, final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.bookService = bookService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BookViewDto> getAllBooks() {
        List<Book> bookEntities = bookService.getAll();
        List<BookViewDto> booksDto = bookEntities.stream()
                .map(b -> modelMapper.map(b, BookViewDto.class)).collect(Collectors.toList());
        return booksDto;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookViewDto getBook(@PathVariable("id") Long id) {
        Book bookEntity = bookService.get(id);
        BookViewDto bookDto = modelMapper.map(bookEntity, BookViewDto.class);
        return bookDto;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BookCreateDto createBook(@RequestBody BookCreateDto bookDto) {
        Book bookEntity = modelMapper.map(bookDto, Book.class);
        bookEntity = bookService.create(bookEntity);
        bookDto = modelMapper.map(bookEntity, BookCreateDto.class);
        return bookDto;
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BookUpdateDto updateBook(@RequestBody BookUpdateDto bookDto) {
        Book bookEntity = modelMapper.map(bookDto, Book.class);
        bookEntity = bookService.update(bookEntity);
        bookDto = modelMapper.map(bookEntity, BookUpdateDto.class);
        return bookDto;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable("id") Long id) {
        bookService.delete(id);
    }

}
