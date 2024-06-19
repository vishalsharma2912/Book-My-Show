package com.acciojob.BookMyShowMAY.Controller;

import com.acciojob.BookMyShowMAY.Requests.AddTheaterRequest;
import com.acciojob.BookMyShowMAY.Requests.AddTheaterSeatRequest;
import com.acciojob.BookMyShowMAY.Services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("theater")
public class TheaterController {

    @Autowired
    private TheaterService theaterServiceObj;

    @PostMapping("add")
    public ResponseEntity addTheater(@RequestBody AddTheaterRequest theaterRequestobj){
        String response=theaterServiceObj.addTheater(theaterRequestobj);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping("associateSeats")
    public ResponseEntity associateSeats(@RequestBody AddTheaterSeatRequest theaterSeatRequest){
        String response=theaterServiceObj.associateTheaterSeats(theaterSeatRequest);
        return new ResponseEntity(response,HttpStatus.OK);
    }

    @GetMapping("get-all-theater-by-movie-name/{movieName}")
    public ResponseEntity<List<String>> getAllTheatreByMovieName(@PathVariable("movieName") String movieName){
        List<String> theatreList=theaterServiceObj.getAllTheatreNameByMovie(movieName);
        return new ResponseEntity<>(theatreList,HttpStatus.OK);
    }
}
