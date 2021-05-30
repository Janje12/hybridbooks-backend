package com.example.hybridbooksbackend.service;

import com.example.hybridbooksbackend.exception.EntityNotFoundException;
import com.example.hybridbooksbackend.model.Book;
import com.example.hybridbooksbackend.model.Reservation;
import com.example.hybridbooksbackend.model.User;
import com.example.hybridbooksbackend.repository.BookRepository;
import com.example.hybridbooksbackend.repository.ReservationRepository;
import com.example.hybridbooksbackend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public ReservationServiceImpl(final ReservationRepository reservationRepository, final UserRepository userRepository,
                                  final BookRepository bookRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public Reservation create(Reservation reservation) {
        Reservation foundReservation = null;
        if (reservation.getId() != null)
            foundReservation = this.get(reservation.getId());
        if (foundReservation == null) {
            Optional<User> user = this.userRepository.findById(reservation.getUser().getId());
            Optional<Book> book = this.bookRepository.findById(reservation.getBook().getId());
            reservation.setBook(book.get());
            reservation.setUser(user.get());
            Reservation newReservation = this.reservationRepository.save(reservation);
            return newReservation;
        } else {
            throw new EntityNotFoundException("That reservation already exists!");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Reservation get(Long id) {
        Optional<Reservation> reservation = this.reservationRepository.findById(id);
        if (reservation.isPresent())
            return reservation.get();
        else
            throw new EntityNotFoundException("No reservation with id: " + id);
    }

    @Override
    @Transactional
    public Reservation update(Reservation reservation) {
        Reservation foundReservation = this.get(reservation.getId());
        if (foundReservation != null) {
            Reservation newReservation = new Reservation(reservation.getId(), reservation.getDateRented(),
                    reservation.getDateReturnal(), reservation.isReturned(), reservation.getUser(), reservation.getBook());
            newReservation = this.reservationRepository.save(newReservation);
            return newReservation;
        } else {
            throw new EntityNotFoundException("No such reservation exists!");
        }
    }

    @Override
    public void delete(Long id) {
        Reservation reservation = this.get(id);
        if (reservation != null) {
            Reservation deletedReservation = reservation;
            this.reservationRepository.deleteById(deletedReservation.getId());
        } else {
            throw new EntityNotFoundException("No reservation with id: " + id);
        }
    }
}
