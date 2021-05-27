package com.example.hybridbooksbackend.service;

import com.example.hybridbooksbackend.model.BookEntity;
import com.example.hybridbooksbackend.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public BookEntity createBook(BookEntity book) throws Exception {
        BookEntity foundBook = null;
        if (book.getIdBook() != 0)
            foundBook = this.getBook("id", book.getIdBook() + "");
        if (foundBook == null) {
            BookEntity newBook = this.bookRepository.save(book);
            return newBook;
        } else {
            throw new Exception("That book already exists!");
        }
    }

    @Override
    public List<BookEntity> getAllBooks() {
        List<BookEntity> bookList = bookRepository.findAll();
        if (bookList.size() > 0) {
            return bookList;
        } else {
            return new ArrayList<BookEntity>();
        }
    }

    @Override
    public BookEntity getBook(String type, String value) throws Exception {
        Optional<BookEntity> book = Optional.empty();
        if (type.equals("id"))
            book = this.bookRepository.findById(Long.parseLong(value));
        else if (type.equals("title"))
            book = this.bookRepository.findByTitle(value);
        else if (type.equals("description"))
            book = this.bookRepository.findByDescriptionContaining(value);
        else if (type.equals("author"))
            book = this.bookRepository.findByAuthor(value);
        else if (type.equals("yearReleased"))
            book = this.bookRepository.findByYearReleased(Integer.parseInt(value));
        else if (type.equals("coverImage"))
            book = this.bookRepository.findByCoverImage(value);
        else if (type.equals("currentAmount"))
            book = this.bookRepository.findByCurrentAmount(Integer.parseInt(value));
        else if (type.equals("maxAmount"))
            book = this.bookRepository.findByMaxAmount(Integer.parseInt(value));

        if (book.isPresent())
            return book.get();
        else
            throw new Exception("No book with " + type + ": " + value);
    }

    @Override
    public BookEntity updateBook(BookEntity book) throws Exception {
        BookEntity foundBook = this.getBook("id", book.getIdBook() + "");
        if (foundBook != null) {
            BookEntity newBook = foundBook;
            newBook.setTitle(book.getTitle());
            newBook.setAuthor(book.getAuthor());
            newBook.setCoverImage(book.getCoverImage());
            newBook.setDescription(book.getDescription());
            newBook.setCurrentAmount(book.getCurrentAmount());
            newBook.setMaxAmount(book.getMaxAmount());
            newBook.setYearReleased(book.getYearReleased());

            newBook = this.bookRepository.save(newBook);
            return newBook;
        } else {
            throw new Exception("No such book exists!");
        }
    }

    @Override
    public BookEntity deleteBook(String type, String value) throws Exception {
        BookEntity book = this.getBook(type, value);
        if (book != null) {
            BookEntity deletedBook = book;
            this.bookRepository.deleteById(deletedBook.getIdBook());
            return book;
        } else {
            throw new Exception("No book with " + type + ": " + value);
        }
    }
}
