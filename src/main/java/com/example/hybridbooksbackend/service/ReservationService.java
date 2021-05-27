package com.example.hybridbooksbackend.service;

import com.example.hybridbooksbackend.model.ReservationEntity;

import java.util.List;

public interface ReservationService {
    ReservationEntity createReservation(ReservationEntity reservation) throws Exception;
    List<ReservationEntity> getAllReservations();
    ReservationEntity getReservation(String type, String value) throws Exception;
    ReservationEntity updateReservation(ReservationEntity reservation) throws Exception;
    ReservationEntity deleteReservation(String type, String value) throws Exception;
}
