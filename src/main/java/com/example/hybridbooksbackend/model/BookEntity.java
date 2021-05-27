package com.example.hybridbooksbackend.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "book", schema = "hybrid_books", catalog = "")
public class BookEntity {
    private long idBook;
    private String title;
    private String description;
    private String author;
    private int yearReleased;
    private String coverImage;
    private int currentAmount;
    private int maxAmount;
    private Collection<ReservationEntity> reservationsByIdBook;

    @Id
    @Column(name = "id_book")
    public long getIdBook() {
        return idBook;
    }

    public void setIdBook(long idBook) {
        this.idBook = idBook;
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
        BookEntity that = (BookEntity) o;
        return idBook == that.idBook && yearReleased == that.yearReleased && currentAmount == that.currentAmount && maxAmount == that.maxAmount && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(author, that.author) && Objects.equals(coverImage, that.coverImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBook, title, description, author, yearReleased, coverImage, currentAmount, maxAmount);
    }

    @OneToMany(mappedBy = "bookByIdBook")
    public Collection<ReservationEntity> getReservationsByIdBook() {
        return reservationsByIdBook;
    }

    public void setReservationsByIdBook(Collection<ReservationEntity> reservationsByIdBook) {
        this.reservationsByIdBook = reservationsByIdBook;
    }
}
