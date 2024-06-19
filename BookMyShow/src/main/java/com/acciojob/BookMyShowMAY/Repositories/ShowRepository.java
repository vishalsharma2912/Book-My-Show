package com.acciojob.BookMyShowMAY.Repositories;

import com.acciojob.BookMyShowMAY.Models.Movie;
import com.acciojob.BookMyShowMAY.Models.Show;
import com.acciojob.BookMyShowMAY.Models.Theater;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show,Integer> {

    @Query(value = "select *from shows where theater_theater_id=?1",nativeQuery = true)
    List<Show> findAllByTheater_TheaterId(Integer theaterId);

    //another way
    /* @Query(value = "SELECT *FROM shows WHERE theater_theater_id = :theaterId", nativeQuery = true)
    * List<Show> findAllByTheater_TheaterId(@Param("theaterId")Integer theaterId);*/


    @Query(value = "select *from shows where movie_movie_id=?1",nativeQuery = true)
    List<Show> findAllByMovie_MovieId(Integer movieId);

    @Query(value = "SELECT * FROM shows WHERE show_date = ?1 AND theater_theater_id = ?2", nativeQuery = true)
    List<Show> findAllByDateAndTheaterId(Date date,Integer theaterId);


    //another ways to write native query
  /* @Query(value = "SELECT * FROM shows WHERE show_date = :date AND theater_theater_id = :theaterId", nativeQuery = true)
    List<Show> findAllByDateAndTheaterId(@Param("date") Date date, @Param("theaterId")Integer theaterId);*/

}
