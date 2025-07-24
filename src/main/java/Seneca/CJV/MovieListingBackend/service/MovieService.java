package Seneca.CJV.MovieListingBackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import Seneca.CJV.MovieListingBackend.model.Movie;
import Seneca.CJV.MovieListingBackend.repository.MovieRepository;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
    public List<Movie> getMovieByTitle(String title) {
        return movieRepository.findByTitle(title);
    }
}