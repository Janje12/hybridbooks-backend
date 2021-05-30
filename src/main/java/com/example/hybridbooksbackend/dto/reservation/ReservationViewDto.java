package com.example.hybridbooksbackend.dto.reservation;

import com.example.hybridbooksbackend.model.Book;
import com.example.hybridbooksbackend.model.User;
import lombok.*;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReservationViewDto {
    @Id
    @NotNull
    private Long id;
    @NotNull
    private Date dateRented;
    @NotNull
    private Date dateReturnal;
    @NotNull
    private boolean returned;
    @NotNull
    private User user;
    @NotNull
    private Book book;
}
