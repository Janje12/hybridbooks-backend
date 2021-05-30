package com.example.hybridbooksbackend.service;

import com.example.hybridbooksbackend.model.Reservation;

import java.util.List;

public interface ReservationService {
    Reservation create(Reservation reservation);
    List<Reservation> getAll();
    Reservation get(Long id);
    Reservation update(Reservation reservation);
    void delete(Long id);
}
