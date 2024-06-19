package com.acciojob.BookMyShowMAY.Repositories;

import com.acciojob.BookMyShowMAY.Models.Show;
import com.acciojob.BookMyShowMAY.Models.ShowSeat;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Integer> {

}
