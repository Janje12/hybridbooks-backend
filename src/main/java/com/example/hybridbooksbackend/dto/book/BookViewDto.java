package com.example.hybridbooksbackend.dto.book;

import lombok.*;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookViewDto {
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
    private int yearReleased;
    private String coverImage;
    @NotNull
    private int currentAmount;
    @NotNull
    private int maxAmount;
}
