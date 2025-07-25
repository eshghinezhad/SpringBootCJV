package Seneca.CJV.MovieListingBackend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public  Movie createMovie(@RequestBody Movie movie) {
        return movieService.createMovie(movie);
    }
    //  retrieves all the movies and tv shows in the database
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMoviesTvs() {
        List<Movie> movies = movieService.getAllMoviesTvs();
        return ResponseEntity.ok(movies != null ? movies : List.of());
    }
    // 2- retrieves all the movies in the database
    @GetMapping("/movie")
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    // 3- retrieves all the tv shows in the database
    @GetMapping("/tv")
    public ResponseEntity<List<Movie>> getAllTvShows() {
        return ResponseEntity.ok(movieService.getAllTvShows());
    }
    
    // 4- retrieves a list of movies and/or tv shows that contains the title
    //    specified in the request parameter 
    @GetMapping("/search")
    public ResponseEntity<List<Movie>> getMovieByTitle(@RequestParam String title) {
        List<Movie> movies = movieService.getMovieByTitle(title);
        return ResponseEntity.ok(movies != null ? movies : List.of());
    }

    // 5-6- retrieves a list of featured movies or tv shows based on the type
    //    specified in the request parameter
    @GetMapping("/featured")
    public ResponseEntity<List<Movie>> getFeatured(@RequestParam  String type) {
        return ResponseEntity.ok(movieService.getFeatured(type));
    }

    // 7- retrieve a specific movie or tv show by ID
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable String id) {
        Movie movie = movieService.getMovieById(id);
        if (movie == null) {
            throw new IllegalArgumentException("Invalid movie ID: " + id);
        }
        return ResponseEntity.ok(movie);
    }
}