package com.example.hybridbooksbackend.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ReservationEntityPK implements Serializable {
    private long idReservation;
    private long idUser;
    private long idBook;

    @Column(name = "id_reservation")
    @Id
    public long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(long idReservation) {
        this.idReservation = idReservation;
    }

    @Column(name = "id_user")
    @Id
    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    @Column(name = "id_book")
    @Id
    public long getIdBook() {
        return idBook;
    }

    public void setIdBook(long idBook) {
        this.idBook = idBook;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationEntityPK that = (ReservationEntityPK) o;
        return idReservation == that.idReservation && idUser == that.idUser && idBook == that.idBook;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReservation, idUser, idBook);
    }
}
