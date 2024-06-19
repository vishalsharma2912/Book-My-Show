package com.acciojob.BookMyShowMAY.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponse {

    private String bookedSeats;

    private LocalDate showDate;

    private LocalTime showTime;

    private String movieName;

    private String theaterName;

    private Integer totalAmount;

}
