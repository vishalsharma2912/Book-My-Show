package com.acciojob.BookMyShowMAY.Requests;

import lombok.Data;

@Data
public class AddTheaterSeatRequest { //DTO are the custom classes tp take input from user

    private Integer theaterId;
    private Integer noOfClassicSeats;
    private Integer noOfPremiumSeats;
}
