package com.example.hybridbooksbackend.controller;

import com.example.hybridbooksbackend.dto.reservation.ReservationCreateDto;
import com.example.hybridbooksbackend.dto.reservation.ReservationUpdateDto;
import com.example.hybridbooksbackend.dto.reservation.ReservationViewDto;
import com.example.hybridbooksbackend.model.Reservation;
import com.example.hybridbooksbackend.service.ReservationService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationController {

    private final ReservationService reservationService;
    private final ModelMapper modelMapper;

    public ReservationController(final ReservationService reservationService, final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.reservationService = reservationService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ReservationViewDto> getAllReservations() {
        List<Reservation> reservationEntities = reservationService.getAll();
        List<ReservationViewDto> reservationDtos = reservationEntities.stream()
                .map(b -> modelMapper.map(b, ReservationViewDto.class)).collect(Collectors.toList());
        return reservationDtos;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReservationViewDto getReservation(@PathVariable("id") Long id) {
        Reservation reservationEntity = reservationService.get(id);
        ReservationViewDto reservationDto = modelMapper.map(reservationEntity, ReservationViewDto.class);
        return reservationDto;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ReservationCreateDto createReservation(@RequestBody ReservationCreateDto reservationDto) {
        Reservation reservationEntity = modelMapper.map(reservationDto, Reservation.class);
        reservationEntity = reservationService.create(reservationEntity);
        reservationDto = modelMapper.map(reservationEntity, ReservationCreateDto.class);
        return reservationDto;
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ReservationUpdateDto updateReservation(@RequestBody ReservationUpdateDto reservationDto) {
        Reservation reservationEntity = modelMapper.map(reservationDto, Reservation.class);
        reservationEntity = reservationService.update(reservationEntity);
        reservationDto = modelMapper.map(reservationEntity, ReservationUpdateDto.class);
        return reservationDto;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteReservation(@PathVariable("id") Long id) {
        reservationService.delete(id);
    }
}
