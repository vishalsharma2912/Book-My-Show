package com.acciojob.BookMyShowMAY.Services;

import com.acciojob.BookMyShowMAY.Models.*;
import com.acciojob.BookMyShowMAY.Repositories.MovieRepository;
import com.acciojob.BookMyShowMAY.Repositories.ShowRepository;
import com.acciojob.BookMyShowMAY.Repositories.ShowSeatRepository;
import com.acciojob.BookMyShowMAY.Repositories.TheaterRepository;
import com.acciojob.BookMyShowMAY.Requests.AddShowRequest;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ShowService {
    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    public String addShow(AddShowRequest showRequest){

        //getting the movie Entity
        Movie movie=movieRepository.findMovieByMovieName(showRequest.getMovieName());

        //getting theater entity
        Theater theater=theaterRepository.findById(showRequest.getTheaterId()).get();

        Show show=Show.builder().showDate(showRequest.getShowDate())
                .showTime(showRequest.getShowTime())
                .movie(movie)  //mapping with movie
                .theater(theater) //mapping with theater
                .build();
        show=showRepository.save(show);

        //associate the corresponding showseats along with it
        List<TheaterSeat> theaterSeatList=theater.getTheaterSeatList();

        List<ShowSeat> showSeatList=new ArrayList<>();
        for(TheaterSeat theaterSeat:theaterSeatList){
            ShowSeat showSeatObj=ShowSeat.builder().seatNo(theaterSeat.getSeatNo())
                    .seatType(theaterSeat.getSeatType())
                    .isBooked(Boolean.FALSE)
                    .isFoodAttached(Boolean.FALSE)
                    .show(show)
                    .build();
            showSeatList.add(showSeatObj);
        }
       //setting the Bidirectional mapping attribute
        show.setShowSeatList(showSeatList);

        showSeatRepository.saveAll(showSeatList);
        return "Show saved with id="+show.getShowId();
    }

    public List<Show> findAllShowByDate(Date date,Integer theaterId){
        List<Show> showList=showRepository.findAllByDateAndTheaterId(date,theaterId);
        return showList;













//        Integer totalAmt=0;
//        int cntClassicSeatBook=0;
//        int cntPremiumSeatBook=0;
//        for(Show show:showList){
//            List<ShowSeat> showSeatList=show.getShowSeatList();
//            for(ShowSeat showSeat:showSeatList){
//                if(showSeat.getIsBooked()){
//                    if(showSeat.getSeatType().equals("Classic")){
//                        cntClassicSeatBook++;
//                    }
//                    else{
//                        cntPremiumSeatBook++;
//                    }
//                }
//            }
//        }
//        totalAmt=cntPremiumSeatBook*100+cntPremiumSeatBook*150;
 //       System.out.println(totalAmt);

    }
}
