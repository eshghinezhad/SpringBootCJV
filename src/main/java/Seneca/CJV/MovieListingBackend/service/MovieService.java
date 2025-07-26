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

    public List<Movie> getAllMoviesTvs() {
        return movieRepository.findAll();
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findByTypeContainingIgnoreCase("movie");
    }

    public List<Movie> getAllTvShows() {
        return movieRepository.findByTypeContainingIgnoreCase("tvShow");
    }

    public List<Movie> getMoviesByTitle(String title) throws Exception {
        List<Movie> movies = movieRepository.findByTitleContainingIgnoreCase(title);
        if (movies.isEmpty()) {
            throw new Exception("No movies found with title: " + title);
        }
        return movies;
    }

    public List<Movie> getFeatured(String type) {
        return movieRepository.findByFeaturedNotNullAndTypeContainingIgnoreCase(type);
    }

    public Movie getMovieById(String id) throws Exception {
        return movieRepository.findById(id)
                .orElseThrow(() -> new Exception("Movie with id " + id + " is not found"));
    }
}