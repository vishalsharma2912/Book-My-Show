package com.acciojob.BookMyShowMAY.Controller;

import com.acciojob.BookMyShowMAY.Models.Show;
import com.acciojob.BookMyShowMAY.Requests.AddShowRequest;
import com.acciojob.BookMyShowMAY.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("show")
public class ShowController {
    @Autowired
    private ShowService showService;

    @PostMapping("add")
    public ResponseEntity addShow(@RequestBody AddShowRequest showRequest){
        String response= showService.addShow(showRequest);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("find-shows-by-theaterID-and-date")
    public ResponseEntity<List<Show>> findAllShowByDateAndTheaterId( @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
                                                       @RequestParam("theaterId") Integer theaterId){
        List<Show> showList=showService.findAllShowByDate(date,theaterId);
        System.out.print(showList.size());
        return new ResponseEntity<>(showList,HttpStatus.OK);
    }

}
