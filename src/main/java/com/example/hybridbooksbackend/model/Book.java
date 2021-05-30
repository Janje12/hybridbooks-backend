package com.example.hybridbooksbackend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Long id;
    private String title;
    private String description;
    private String author;
    private int yearReleased;
    private String coverImage;
    private int currentAmount;
    private int maxAmount;
    @JsonManagedReference(value = "bookReservations")
    private Collection<Reservation> reservations;

    public Book(Long id, String title, String description, String author, int yearReleased, String coverImage, int currentAmount, int maxAmount) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.yearReleased = yearReleased;
        this.coverImage = coverImage;
        this.currentAmount = currentAmount;
        this.maxAmount = maxAmount;
    }

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "year_released")
    public int getYearReleased() {
        return yearReleased;
    }

    public void setYearReleased(int yearReleased) {
        this.yearReleased = yearReleased;
    }

    @Basic
    @Column(name = "cover_image")
    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    @Basic
    @Column(name = "current_amount")
    public int getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(int currentAmount) {
        this.currentAmount = currentAmount;
    }

    @Basic
    @Column(name = "max_amount")
    public int getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return yearReleased == book.yearReleased && currentAmount == book.currentAmount && maxAmount == book.maxAmount && Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(description, book.description) && Objects.equals(author, book.author) && Objects.equals(coverImage, book.coverImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, author, yearReleased, coverImage, currentAmount, maxAmount);
    }

    @OneToMany(mappedBy = "book")
    public Collection<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Collection<Reservation> reservations) {
        this.reservations = reservations;
    }
}
