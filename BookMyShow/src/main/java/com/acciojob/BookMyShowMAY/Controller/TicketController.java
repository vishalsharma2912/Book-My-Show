package com.acciojob.BookMyShowMAY.Controller;

import com.acciojob.BookMyShowMAY.Repositories.TicketRepository;
import com.acciojob.BookMyShowMAY.Requests.BookTicketRequest;
import com.acciojob.BookMyShowMAY.Response.TicketResponse;
import com.acciojob.BookMyShowMAY.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ticket")
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketService ticketService;

    @PostMapping("book")
    public ResponseEntity bookTicket(@RequestBody BookTicketRequest ticketRequest){
        String response= ticketService.bookTicket(ticketRequest);
        return new ResponseEntity(response, HttpStatus.OK);

    }

    @GetMapping("generate")
    public TicketResponse generateTicket(@RequestParam("ticketId") String ticketId) {

        return ticketService.generateTicket(ticketId);
    }
}
