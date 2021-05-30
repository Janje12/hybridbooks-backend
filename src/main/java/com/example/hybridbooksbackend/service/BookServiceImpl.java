package com.example.hybridbooksbackend.service;

import com.example.hybridbooksbackend.exception.EntityNotFoundException;
import com.example.hybridbooksbackend.model.Book;
import com.example.hybridbooksbackend.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public Book create(Book book) {
        Book foundBook = null;
        if (book.getId() != null)
            foundBook = this.get(book.getId());
        if (foundBook == null) {
            Book newBook = this.bookRepository.save(book);
            return newBook;
        } else {
            throw new EntityNotFoundException("That book already exists!");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Book get(Long id) {
        Optional<Book> book = this.bookRepository.findById(id);
        if (book.isPresent())
            return book.get();
        else
            throw new EntityNotFoundException("No book with id:" + id);
    }

    @Override
    @Transactional
    public Book update(Book book) {
        Book foundBook = this.get(book.getId());
        if (foundBook != null) {
            Book newBook = new Book(book.getId(), book.getTitle(), book.getDescription(), book.getAuthor(), book.getYearReleased(),
                    book.getCoverImage(), book.getCurrentAmount(), book.getMaxAmount());
            newBook = this.bookRepository.save(newBook);
            return newBook;
        } else {
            throw new EntityNotFoundException("No such book exists!");
        }
    }

    @Override
    public void delete(Long id) {
        Book book = this.get(id);
        if (book != null) {
            Book deletedBook = book;
            this.bookRepository.deleteById(deletedBook.getId());
        } else {
            throw new EntityNotFoundException("No book with id: " + id);
        }
    }
}
