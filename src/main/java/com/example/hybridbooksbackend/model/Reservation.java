package com.example.hybridbooksbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reservation {
    private Long id;
    private Date dateRented;
    private Date dateReturnal;
    private boolean returned;
    private User user;
    private Book book;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_rented", nullable = false, updatable = false)
    @CreatedDate
    public Date getDateRented() {
        return dateRented;
    }

    public void setDateRented(Date dateRented) {
        this.dateRented = dateRented;
    }

    @Basic
    @Column(name = "date_returnal")
    public Date getDateReturnal() {
        return dateReturnal;
    }

    public void setDateReturnal(Date dateReturnal) {
        this.dateReturnal = dateReturnal;
    }

    @Basic
    @Column(name = "returned")
    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return returned == that.returned && Objects.equals(id, that.id) && Objects.equals(dateRented, that.dateRented) && Objects.equals(dateReturnal, that.dateReturnal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateRented, dateReturnal, returned);
    }

    @ManyToOne
    @Fetch(FetchMode.SELECT)
    @JsonBackReference(value = "userReservations")
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @Fetch(FetchMode.SELECT)
    @JsonBackReference(value = "bookReservations")
    @JoinColumn(name = "id_book", referencedColumnName = "id", nullable = false)
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
