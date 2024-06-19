package com.acciojob.BookMyShowMAY.Repositories;

import com.acciojob.BookMyShowMAY.Models.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository extends JpaRepository<Theater,Integer> {
    Theater findTheaterByName(String theaterName);
}
