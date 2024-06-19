package com.acciojob.BookMyShowMAY.Repositories;

import com.acciojob.BookMyShowMAY.Models.TheaterSeat;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TheaterSeatRepository extends JpaRepository<TheaterSeat, Integer> {

}
