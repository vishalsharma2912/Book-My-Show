package com.acciojob.BookMyShowMAY.Requests;

import com.acciojob.BookMyShowMAY.Enum.Genre;
import com.acciojob.BookMyShowMAY.Enum.Language;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddMovieRequest {

    private String movieName;
    private Double duration;
    private LocalDate releaseDate;
    private Language language;
    private Genre genre;
    private Double rating;
}
