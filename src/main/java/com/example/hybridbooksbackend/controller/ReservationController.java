package com.example.hybridbooksbackend.controller;

import com.example.hybridbooksbackend.model.ReservationEntity;
import com.example.hybridbooksbackend.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/reservation", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReservationEntity>> getAllReservations() {
        List<ReservationEntity> list = reservationService.getAllReservations();
        System.err.println(list);
        return new ResponseEntity<List<ReservationEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{type}/{value}")
    public ResponseEntity<ReservationEntity> getReservation(@PathVariable("type") String type, @PathVariable("value") String value)
            throws Exception {
        ReservationEntity entity = reservationService.getReservation(type, value);
        return new ResponseEntity<ReservationEntity>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    /* Proveri kako da ID sam pravi Book i User */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationEntity> createReservation(@RequestBody ReservationEntity user) throws Exception {
        ReservationEntity created = reservationService.createReservation(user);
        return new ResponseEntity<ReservationEntity>(created, new HttpHeaders(), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<ReservationEntity> updateReservation(ReservationEntity user)
            throws Exception {
        ReservationEntity updated = reservationService.updateReservation(user);
        return new ResponseEntity<ReservationEntity>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{type}/{value}")
    public ResponseEntity<ReservationEntity> deleteReservation(@PathVariable("type") String type, @PathVariable("value") String value)
            throws Exception {
        ReservationEntity deleted = reservationService.deleteReservation(type, value);
        return new ResponseEntity<ReservationEntity>(deleted, new HttpHeaders(), HttpStatus.OK);
    }
}
