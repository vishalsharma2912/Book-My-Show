package com.acciojob.BookMyShowMAY.Services;

import com.acciojob.BookMyShowMAY.Enum.Genre;
import com.acciojob.BookMyShowMAY.Enum.Language;
import com.acciojob.BookMyShowMAY.Models.Movie;
import com.acciojob.BookMyShowMAY.Models.Show;
import com.acciojob.BookMyShowMAY.Models.Theater;
import com.acciojob.BookMyShowMAY.Repositories.MovieRepository;
import com.acciojob.BookMyShowMAY.Repositories.ShowRepository;
import com.acciojob.BookMyShowMAY.Repositories.TheaterRepository;
import com.acciojob.BookMyShowMAY.Requests.AddMovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.xml.MarshallingView;

import java.util.*;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    public String addMovie(AddMovieRequest movieRequest){

        //from my request Entry i am creating the entity:bcz entity saves into the DB
        Movie movie=new Movie();

        movie.setMovieName(movieRequest.getMovieName());
        movie.setLanguage(movieRequest.getLanguage());
        movie.setDuration(movieRequest.getDuration());
        movie.setRating(movieRequest.getRating());
        movie.setReleaseDate(movieRequest.getReleaseDate());
        movie.setGenre(movieRequest.getGenre());

        //saved to Db
        movie=movieRepository.save(movie);

        return "Movie Added with id="+movie.getMovieId();
    }

    public List<String> recommendMoviesByGenre(String genre){
        List<Movie> movieList=movieRepository.findAll();
        List<String> movieNameList=new ArrayList<>();

        Genre requestedGenre = Genre.valueOf(genre.toUpperCase()); // Convert the String to Genre enum
        for(Movie movie:movieList){
            if(movie.getGenre().equals(requestedGenre)){
                movieNameList.add(movie.getMovieName());
            }
        }
        return movieNameList;

        //we can also use findAllByGenre(genre) and return list directly
    }

    public List<String> topThreeMoviesBasedOnLanguage(String language){
        Language requestedGenre = Language.valueOf(language); // Convert the String to Genre enum

        List<Movie> movieList=movieRepository.findAllByLanguage(requestedGenre);

        List<String> ansList=new ArrayList<>();

        // Sort movies by rating in descending order
        movieList.sort(Comparator.comparingDouble(Movie::getRating).reversed());

        int no=0;
        for(Movie movie:movieList){
            if(no==3){
                break;
            }
            ansList.add(movie.getMovieName());
            no++;
        }
        return ansList;
    }
    public List<String> getAllMoviesByTheater(String theaterName){


        Theater theater=theaterRepository.findTheaterByName(theaterName);
        Integer theaterId=theater.getTheaterId();


        List<Show> showList=showRepository.findAllByTheater_TheaterId(theaterId);


        List<String> movieList=new ArrayList<>();
        for(Show show:showList){
            String movieName=show.getMovie().getMovieName();
            if(!movieList.contains(movieName)){
                movieList.add(movieName);
            }
        }
        return movieList;
    }
}
