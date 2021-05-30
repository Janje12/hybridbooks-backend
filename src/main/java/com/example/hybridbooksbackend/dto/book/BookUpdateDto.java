package com.example.hybridbooksbackend.dto.book;

import lombok.*;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookUpdateDto {
    @Id
    @NotNull
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private String author;
    @NotNull
    private String coverImage;
    @NotNull
    private int yearReleased;
    @NotNull
    private int maxAmount;
    @NotNull
    private int currentAmount;
}
