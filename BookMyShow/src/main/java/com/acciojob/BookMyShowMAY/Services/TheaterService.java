package com.acciojob.BookMyShowMAY.Services;

import com.acciojob.BookMyShowMAY.Enum.SeatType;
import com.acciojob.BookMyShowMAY.Models.Movie;
import com.acciojob.BookMyShowMAY.Models.Show;
import com.acciojob.BookMyShowMAY.Models.Theater;
import com.acciojob.BookMyShowMAY.Models.TheaterSeat;
import com.acciojob.BookMyShowMAY.Repositories.MovieRepository;
import com.acciojob.BookMyShowMAY.Repositories.ShowRepository;
import com.acciojob.BookMyShowMAY.Repositories.TheaterRepository;
import com.acciojob.BookMyShowMAY.Repositories.TheaterSeatRepository;
import com.acciojob.BookMyShowMAY.Requests.AddTheaterRequest;
import com.acciojob.BookMyShowMAY.Requests.AddTheaterSeatRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {

    @Autowired
    private TheaterRepository theaterRepositoryObj;

    @Autowired
    private TheaterSeatRepository theaterSeatRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ShowRepository showRepository;

    public String addTheater(AddTheaterRequest addTheaterRequest){
        Theater theater=Theater.builder().noOfScreen(addTheaterRequest.getNoOfScreen())
                .name(addTheaterRequest.getTheaterName())
                .address(addTheaterRequest.getAddress())
                .build();
        theater=theaterRepositoryObj.save(theater);
        return "Theater saved with id "+theater.getTheaterId();
    }

    public String associateTheaterSeats(AddTheaterSeatRequest theaterSeatRequest){

        int theaterId=theaterSeatRequest.getTheaterId();
        int noOfClassicSeats=theaterSeatRequest.getNoOfClassicSeats();
        int noOfPremimumSeats=theaterSeatRequest.getNoOfPremiumSeats();

        List<TheaterSeat> theaterSeatList=new ArrayList<>();

        //Get theater Entity from DB
        Theater theater=theaterRepositoryObj.findById(theaterId).get();

        //Generate Those seatNo through CLassic Seats
        int noOfClassicSeatsRows=noOfClassicSeats/5;    //here we are taking 5 rows by default
        int noOfClassicSeatsInLastRows=noOfClassicSeats%5;

        int row;

        for( row=1;row<=noOfClassicSeatsRows;row++){
            for(int j=1;j<=5;j++){
                char ch=(char)('A'+j-1);
                String seatNo=""+row+ch;

                TheaterSeat theaterSeat=TheaterSeat.builder().seatNo(seatNo)
                        .seatType(SeatType.Classic)
                        .theater(theater)   //mapping with theater
                        .build();
                theaterSeatList.add(theaterSeat);
            }
        }

        // for the last row
        for(int j=1;j<=noOfClassicSeatsInLastRows;j++){
            char ch=(char)('A'+j-1);
            String seatNo=""+row+ch;

            TheaterSeat theaterSeat=TheaterSeat.builder().seatNo(seatNo)
                    .seatType(SeatType.Classic)
                    .theater(theater)   //mapping with theater
                    .build();
            theaterSeatList.add(theaterSeat);
        }

        //same logic for Premium Seats
        int noOfPremiumSeatsRows=noOfPremimumSeats/5;
        int noOfPremiumSeatLastRow=noOfPremimumSeats%5;

        int currentRow=row;
        if(noOfClassicSeatsInLastRows>0){
            currentRow++;
        }

        for(row=currentRow;row<=noOfPremiumSeatsRows+currentRow-1;row++){
            for(int j=1;j<=5;j++){
                char ch=(char)('A'+j-1);
                String seatNo=""+row+ch;

                TheaterSeat theaterSeat=TheaterSeat.builder().seatNo(seatNo)
                        .seatType(SeatType.Premium)
                        .theater(theater)   //mapping with theater
                        .build();
                theaterSeatList.add(theaterSeat);
            }
        }

        //for the last row
        for(int j=1;j<=noOfPremiumSeatLastRow;j++){
            char ch=(char)('A'+j-1);
            String seatNo=""+row+ch;

            TheaterSeat theaterSeat=TheaterSeat.builder().seatNo(seatNo)
                    .seatType(SeatType.Premium)
                    .theater(theater)   //mapping with theater
                    .build();
            theaterSeatList.add(theaterSeat);
        }

        theater.setTheaterSeatList(theaterSeatList);
        theaterRepositoryObj.save(theater);

        //save all the generated Theaterseats into DB
        theaterSeatRepository.saveAll(theaterSeatList);
        return "Theater Seats associated to theater_id="+theaterSeatRequest.getTheaterId();
    }
    public List<String> getAllTheatreNameByMovie(String movieName) {

        //getting movie->movieId from movieName
        Movie movie = movieRepository.findMovieByMovieName(movieName);
        Integer movieId=movie.getMovieId();

        //list of all shows corresponding to movieID
        List<Show> showList=showRepository.findAllByMovie_MovieId(movieId);


        List<String> theatreList=new ArrayList<>();

        //getting Theatre entity->theaterName from showList
        for(Show show:showList){
            String theaterName=show.getTheater().getName();
            if(!theatreList.contains(theaterName)){
                theatreList.add(theaterName);
            }
        }
        return theatreList;
    }

}
