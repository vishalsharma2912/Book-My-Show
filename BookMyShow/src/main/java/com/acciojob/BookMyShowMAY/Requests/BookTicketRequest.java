package com.acciojob.BookMyShowMAY.Requests;

import jdk.dynalink.linker.LinkerServices;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookTicketRequest {
    private List<String> requestedSeats;
    private Integer showId;
    private Integer userId;
}
