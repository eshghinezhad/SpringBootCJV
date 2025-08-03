package Seneca.CJV.MovieListingBackend.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Seneca.CJV.MovieListingBackend.CustomizedResponse;
import Seneca.CJV.MovieListingBackend.model.Movie;
import Seneca.CJV.MovieListingBackend.service.MovieService;
import jakarta.validation.Valid;


@RestController
@CrossOrigin(origins = "http://localhost:3000") 
@RequestMapping("/show")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    // 1- create movies/tv shows to be added to the database
    @PostMapping
    public ResponseEntity<Movie> createMovie(@Valid @RequestBody Movie movie) {
        Movie addedMovie = movieService.createMovie(movie);
        if (addedMovie == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(addedMovie);
    }
    
    //  retrieves all the movies and tv shows in the database
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMoviesTvs() {
        List<Movie> allMoviesTvs = movieService.getAllMoviesTvs();
        if (allMoviesTvs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(allMoviesTvs);
    }
   
    // 2- retrieves all the movies in the database
    @GetMapping("/movie")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> allMovies = movieService.getAllMovies();
        if (allMovies.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(allMovies);
    }

    // 3- retrieves all the tv shows in the database
    @GetMapping("/tv")
    public ResponseEntity<List<Movie>> getAllTvShows() {
        List<Movie> allTvShows = movieService.getAllTvShows();
        if (allTvShows.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(allTvShows);
    }
    
    // 4- retrieves a list of movies and/or tv shows that contains the specified title
    @GetMapping("/search")
    public ResponseEntity<?> getMoviesByTitle(@RequestParam String title) {
        // Validate the title parameter
        if (title == null || title.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Title cannot be null or empty");
        }
        try {
            List<Movie> movies = movieService.getMoviesByTitle(title);
            return ResponseEntity.ok(movies); 
        } catch (Exception e) {
            CustomizedResponse<List<Movie>> customizedResponse = new CustomizedResponse<>(e.getMessage(), null);
            return new ResponseEntity<>(customizedResponse, HttpStatus.NOT_FOUND);
        }
    }

    // 5-6- retrieves a list of featured movies or tv shows based on the specified type
    @GetMapping("/find")
    public ResponseEntity<List<Movie>> getFeatured(@RequestParam String featured) {
        return ResponseEntity.ok(movieService.getFeatured(featured));
    }

    // 7- retrieve a specific movie or tv show by ID + provide validation logic
    @GetMapping("/{id}")
    public ResponseEntity<CustomizedResponse<Movie>> getMovieById(@PathVariable String id) {
        CustomizedResponse<Movie> customizedResponse;
        try {
            Movie movie = movieService.getMovieById(id);
            customizedResponse = new CustomizedResponse<>("Movie with id " + id + " has been retrieved successfully.", Collections.singletonList(movie));
        } catch (Exception e) {
            customizedResponse = new CustomizedResponse<>(e.getMessage(), null);
            return new ResponseEntity<>(customizedResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customizedResponse, HttpStatus.OK);
    }

    // 8- Update and change an existing movie in the database by specific ID + provide validation logic
    @PutMapping("/{id}")
    public ResponseEntity<CustomizedResponse<Movie>> updateMovie( @PathVariable String id, @RequestBody Movie updatedMovie) {
        CustomizedResponse<Movie> customizedResponse;
        try {
            Movie movie = movieService.updateMovie(id, updatedMovie);
            customizedResponse = new CustomizedResponse<>("Movie with id " + id + " has been updated successfully.", Collections.singletonList(movie));
        } catch (Exception e) {
            customizedResponse = new CustomizedResponse<>(e.getMessage(), null);
            return new ResponseEntity<>(customizedResponse, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customizedResponse, HttpStatus.OK);
    }

    // 9- Delete an existing movie or tv show by specific ID + provide validation logic
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomizedResponse<Void>> deleteMovieById(@PathVariable String id) {
        CustomizedResponse<Void> customizedResponse;
        try {
            movieService.deleteMovieById(id);
            customizedResponse = new CustomizedResponse<>("Movie with id " + id + " has been deleted successfully.", null);
        } catch (Exception e) {
            customizedResponse = new CustomizedResponse<>(e.getMessage(), null);
            return new ResponseEntity<>(customizedResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customizedResponse, HttpStatus.OK);
    }
}