package com.acciojob.BookMyShowMAY.Models;

import com.acciojob.BookMyShowMAY.Enum.Genre;
import com.acciojob.BookMyShowMAY.Enum.Language;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;

    @Column(unique = true)
    private String movieName;

    private Double duration;

    private LocalDate releaseDate;

    @Enumerated(value = EnumType.STRING)

    private Language language;

    @Enumerated(value=EnumType.STRING)
    @Column(length = 20)
    private Genre genre;

    private Double rating;
}
