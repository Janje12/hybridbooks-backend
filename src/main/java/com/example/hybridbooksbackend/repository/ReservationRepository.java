package com.example.hybridbooksbackend.repository;

import com.example.hybridbooksbackend.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByBook(Long id);
    Optional<Reservation> findByUser(Long id);
    Optional<Reservation> findByDateRented(Date dateRented);
    Optional<Reservation> findByDateReturnal(Date dateReturnal);
    Optional<Reservation> findByReturned(boolean returned);
}
