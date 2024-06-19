package com.acciojob.BookMyShowMAY.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "tickets")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String ticketId; //auto generated

    private LocalDate showDate; //will be taken from show class

    private LocalTime showTime; //will be taken from show class

    private String movieName; //will be taken from movie class

    private String theaterName; //taken from theater class

    private Integer totalAmt;

    private String bookedSeats;

    @JoinColumn
    @ManyToOne
    private Show show;  //many tickets for one show

    @JoinColumn
    @ManyToOne
    private User user; //many tickets booked by one user

}
