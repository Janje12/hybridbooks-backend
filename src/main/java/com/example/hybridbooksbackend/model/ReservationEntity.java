package com.example.hybridbooksbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "reservation", schema = "hybrid_books", catalog = "")
@IdClass(ReservationEntityPK.class)
public class ReservationEntity {
    private long idReservation;
    private long idUser;
    private long idBook;
    private Date dateRented;
    private Date dateReturnal;
    private boolean returned;
    @JsonBackReference
    private UserEntity userByIdUser;
    @JsonBackReference
    private BookEntity bookByIdBook;

    @Id
    @Column(name = "id_reservation")
    public long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(long idReservation) {
        this.idReservation = idReservation;
    }

    @Id
    @Column(name = "id_user")
    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    @Id
    @Column(name = "id_book")
    public long getIdBook() {
        return idBook;
    }

    public void setIdBook(long idBook) {
        this.idBook = idBook;
    }

    @Basic
    @Column(name = "date_rented")
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
    public boolean getReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationEntity that = (ReservationEntity) o;
        return idReservation == that.idReservation && idUser == that.idUser && idBook == that.idBook && returned == that.returned && Objects.equals(dateRented, that.dateRented) && Objects.equals(dateReturnal, that.dateReturnal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReservation, idUser, idBook, dateRented, dateReturnal, returned);
    }

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false, insertable = false, updatable = false)
    public UserEntity getUserByIdUser() {
        return userByIdUser;
    }

    public void setUserByIdUser(UserEntity userByIdUser) {
        this.userByIdUser = userByIdUser;
    }

    @ManyToOne
    @JoinColumn(name = "id_book", referencedColumnName = "id_book", nullable = false, insertable = false, updatable = false)
    public BookEntity getBookByIdBook() {
        return bookByIdBook;
    }

    public void setBookByIdBook(BookEntity bookByIdBook) {
        this.bookByIdBook = bookByIdBook;
    }
}
