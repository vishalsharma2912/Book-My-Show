package com.acciojob.BookMyShowMAY.Services;

import com.acciojob.BookMyShowMAY.Enum.SeatType;

import com.acciojob.BookMyShowMAY.Models.*;
import com.acciojob.BookMyShowMAY.Repositories.*;
import com.acciojob.BookMyShowMAY.Requests.BookTicketRequest;
import com.acciojob.BookMyShowMAY.Response.TicketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TheaterRepository theaterRepository;

    public String bookTicket(BookTicketRequest bookTicketRequest){

        //1. Find the Show Entity
        Show show = showRepository.findById(bookTicketRequest.getShowId()).get();

        //2. Find the User Entity
        User user = userRepository.findById(bookTicketRequest.getUserId()).get();

        //3. Mark those Seats as booked now and calculate total Amount
        Integer totalAmount = 0;
        List<ShowSeat> showSeatList = show.getShowSeatList();

        for(ShowSeat showSeat : showSeatList) {

            String seatNo = showSeat.getSeatNo();
            if(bookTicketRequest.getRequestedSeats().contains(seatNo)) {
                showSeat.setIsBooked(Boolean.TRUE);
                showSeat.setIsFoodAttached(Boolean.TRUE);
                if(showSeat.getSeatType().equals(SeatType.Classic))
                    totalAmount = totalAmount + 100;
                else
                    totalAmount = totalAmount+150;
            }
        }

        //4. Create the Ticket Entity and set the attributes
        Ticket ticket = Ticket.builder().showDate(show.getShowDate())
                .showTime(show.getShowTime())
                .movieName(show.getMovie().getMovieName())
                .theaterName(show.getTheater().getName())
                .totalAmt(totalAmount)
                .bookedSeats(bookTicketRequest.getRequestedSeats().toString())
                .show(show)
                .user(user)
                .build();


        showSeatRepository.saveAll(showSeatList);
        ticket = ticketRepository.save(ticket);
        //5. save the ticket into DB and return Ticket Entity (Ticket Response)
        return ticket.getTicketId();
    }

    public TicketResponse generateTicket(String ticketId){
        Ticket ticket=ticketRepository.findById(ticketId).get();
        TicketResponse ticketResponse=TicketResponse.builder().bookedSeats(ticket.getBookedSeats())
                .showDate(ticket.getShowDate())
                .showTime(ticket.getShowTime())
                .movieName(ticket.getMovieName())
                .theaterName(ticket.getTheaterName())
                .totalAmount(ticket.getTotalAmt())
                .build();


        //getting user from ticket
        User user=ticket.getUser();

//        String theaterName=ticket.getTheaterName();
//        Theater theater=theaterRepository.findTheaterByName(theaterName);

        //getting theater from theaterName
         Theater theater=theaterRepository.findTheaterByName(ticket.getTheaterName());

        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setTo(user.getEmailId());

        mailMessage.setFrom("se.hasanjafar@gmail.com");
        mailMessage.setSubject("Tickets Booked Successfully");
        String body="Hi "+user.getName()+"\nYour tickets has been booked at "+ticket.getTheaterName()+" mall"
                +"\nAddress="+theater.getAddress()+ "\nMovie ="+ticket.getMovieName()
                +"\nDate="+ticket.getShowDate()
                +"\nTime="+ticket.getShowTime()
                +"\nSeat No="+ticket.getBookedSeats()
                +"\nTotal Amount="+ticket.getTotalAmt()
                +"\n\n This is just an API testing email, sorry for disturbance";

        mailMessage.setText(body);
        javaMailSender.send(mailMessage);

        return ticketResponse;
    }
}
