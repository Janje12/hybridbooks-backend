package com.example.hybridbooksbackend.dto.book;

import lombok.*;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookCreateDto {
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private String author;
    @NotNull
    private int yearReleased;
    @NotNull
    private int maxAmount;
}
