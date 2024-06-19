package com.acciojob.BookMyShowMAY.Models;

import com.acciojob.BookMyShowMAY.Enum.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Showseat")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder   //another method to make an obj of a class
public class ShowSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer showSeatId;
    private String seatNo;

    @Enumerated(value=EnumType.STRING)
    private SeatType seatType;

    private Boolean isBooked;

    private Boolean isFoodAttached;

    @JoinColumn
    @ManyToOne
    private Show show;
}
