package com.example.hybridbooksbackend.repository;

import com.example.hybridbooksbackend.model.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
    Optional<ReservationEntity> findByIdBook(Long id);
    Optional<ReservationEntity> findByIdUser(Long id);
    Optional<ReservationEntity> findByDateRented(Date dateRented);
    Optional<ReservationEntity> findByDateReturnal(Date dateReturnal);
    Optional<ReservationEntity> findByReturned(boolean returned);
}
