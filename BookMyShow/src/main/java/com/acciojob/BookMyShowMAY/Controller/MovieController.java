package com.acciojob.BookMyShowMAY.Controller;

import com.acciojob.BookMyShowMAY.Enum.Genre;
import com.acciojob.BookMyShowMAY.Models.Movie;
import com.acciojob.BookMyShowMAY.Requests.AddMovieRequest;
import com.acciojob.BookMyShowMAY.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movie")

public class MovieController {

  @Autowired
  private MovieService movieServiceObj;

  @PostMapping("add")
    public ResponseEntity addMovie(@RequestBody AddMovieRequest movieRequest){

      String response= movieServiceObj.addMovie(movieRequest);
      return new ResponseEntity(response, HttpStatus.OK);

  }

  @GetMapping("/all-movie-list-based-on-genre/{genre}")
  public ResponseEntity<List<String>> recommendMoviesByGenre(@PathVariable("genre") String genre) {

    List<String> movieList = movieServiceObj.recommendMoviesByGenre(genre);
    return ResponseEntity.ok(movieList);
  }

  @GetMapping("/Top-three-movies-based-on-language/{language}")
  public ResponseEntity<List<String>> TopThreeMoviesBasedOnLanguage(@PathVariable("language") String language){

    List<String> movieList=movieServiceObj.topThreeMoviesBasedOnLanguage(language);
    return ResponseEntity.ok(movieList);
  }
  @GetMapping("movies-List-by-theater-name/{name}")
  public ResponseEntity<List<String>> getAllMovieListByTheaterName(@PathVariable("name") String name){

    List<String> movieList=movieServiceObj.getAllMoviesByTheater(name);
    return ResponseEntity.ok(movieList);
  }
}
