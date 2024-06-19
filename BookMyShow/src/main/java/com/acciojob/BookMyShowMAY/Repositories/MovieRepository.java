package com.acciojob.BookMyShowMAY.Repositories;

import com.acciojob.BookMyShowMAY.Enum.Language;
import com.acciojob.BookMyShowMAY.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {

    Movie findMovieByMovieName(String movieName); //movie name is unique
    List<Movie> findAllByLanguage(Language language);


}

