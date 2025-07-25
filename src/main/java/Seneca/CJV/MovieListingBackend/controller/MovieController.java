package Seneca.CJV.MovieListingBackend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Seneca.CJV.MovieListingBackend.model.Movie;
import Seneca.CJV.MovieListingBackend.service.MovieService;

@RestController
@RequestMapping("/show")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }
    // 1- create movies/tv shows to be added to the database
    @PostMapping
    public Movie createMovie(@RequestBody Movie movie) {
        return movieService.createMovie(movie);
    }
    //  retrieves all the movies and tv shows in the database
    @GetMapping
    public List<Movie> getAllMoviesTvs() {
        return movieService.getAllMoviesTvs();
    }
    // 2- retrieves all the movies in the database
    @GetMapping("/movie")
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    // 3- retrieves all the tv shows in the database
    @GetMapping("/tv")
    public List<Movie> getAllTvShows() {
        return movieService.getAllTvShows();
    }
    
    // 4- retrieves a list of movies and/or tv shows that contains the title
    //    specified in the request parameter 
    @GetMapping("/title")
    public List<Movie> getMovieByTitle(@RequestParam String title) {
        return movieService.getMovieByTitle(title);
    }

    
    
}