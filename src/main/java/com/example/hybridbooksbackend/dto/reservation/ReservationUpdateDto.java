package com.example.hybridbooksbackend.dto.reservation;

import lombok.*;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReservationUpdateDto {
    @Id
    @NotNull
    private Long id;
    @NotNull
    private boolean returned;
}
