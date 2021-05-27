package com.example.hybridbooksbackend.service;

import com.example.hybridbooksbackend.model.ReservationEntity;
import com.example.hybridbooksbackend.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public ReservationEntity createReservation(ReservationEntity reservation) throws Exception {
        ReservationEntity foundReservation = null;
        if (reservation.getIdReservation() != 0)
            foundReservation = this.getReservation("id", reservation.getIdReservation() + "");
        if (foundReservation == null) {
            ReservationEntity newReservation = this.reservationRepository.save(reservation);
            return newReservation;
        } else {
            throw new Exception("That reservation already exists!");
        }
    }

    @Override
    public List<ReservationEntity> getAllReservations() {
        List<ReservationEntity> reservationList = reservationRepository.findAll();
        if (reservationList.size() > 0) {
            return reservationList;
        } else {
            return new ArrayList<ReservationEntity>();
        }
    }

    @Override
    public ReservationEntity getReservation(String type, String value) throws Exception {
        Optional<ReservationEntity> reservation = Optional.empty();
        if (type.equals("id"))
            reservation = this.reservationRepository.findById(Long.parseLong(value));
        else if (type.equals("book"))
            reservation = this.reservationRepository.findByIdBook(Long.parseLong(value));
        else if (type.equals("user"))
            reservation = this.reservationRepository.findByIdUser(Long.parseLong(value));
        else if (type.equals("dateRented"))
            reservation = this.reservationRepository.findByDateRented(new SimpleDateFormat("dd/MM/yyyy").parse(value));
        else if (type.equals("dateReturnal"))
            reservation = this.reservationRepository.findByDateReturnal(new SimpleDateFormat("dd/MM/yyyy").parse(value));
        else if (type.equals("returned"))
            reservation = this.reservationRepository.findByReturned(value.equals("true"));

        if (reservation.isPresent())
            return reservation.get();
        else
            throw new Exception("No reservation with " + type + ": " + value);
    }

    @Override
    public ReservationEntity updateReservation(ReservationEntity reservation) throws Exception {
        ReservationEntity foundReservation = this.getReservation("id", reservation.getIdReservation() + "");
        if (foundReservation != null) {
            ReservationEntity newReservation = foundReservation;
            newReservation.setDateRented(reservation.getDateRented());
            newReservation.setDateReturnal(reservation.getDateReturnal());
            newReservation.setReturned(reservation.getReturned());
            newReservation.setIdBook(reservation.getIdBook());
            newReservation.setIdUser(reservation.getIdUser());
            /* Check if this works */
            newReservation.setUserByIdUser(reservation.getUserByIdUser());
            newReservation.setBookByIdBook(reservation.getBookByIdBook());

            newReservation = this.reservationRepository.save(newReservation);
            return newReservation;
        } else {
            throw new Exception("No such reservation exists!");
        }
    }

    @Override
    public ReservationEntity deleteReservation(String type, String value) throws Exception {
        ReservationEntity reservation = this.getReservation(type, value);
        if (reservation != null) {
            ReservationEntity deletedReservation = reservation;
            this.reservationRepository.deleteById(deletedReservation.getIdReservation());
            return reservation;
        } else {
            throw new Exception("No reservation with " + type + ": " + value);
        }
    }
}
