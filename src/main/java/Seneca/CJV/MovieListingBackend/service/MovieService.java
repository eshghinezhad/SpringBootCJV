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
            throw new Exception("Movie containing title " + title + " is not found");
        }
        return movies;
    }

    public List<Movie> getFeatured(String featured) {
        return movieRepository.findByFeaturedContainingIgnoreCase(featured);
    }

    public Movie getMovieById(String id) throws Exception {
        return movieRepository.findById(id)
                .orElseThrow(() -> new Exception("Movie with id " + id + " is not found"));
    }

    public Movie updateMovie(String id, Movie updatedMovie) throws Exception {
        // Validate if the movie exists
        Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(() -> new Exception("Movie with id " + id + " is not found."));

        // Since Movie is a record (immutable), you cannot use setters.
        // Instead, create a new Movie instance with updated fields.
        Movie newMovie = new Movie(
            existingMovie.id(),
            updatedMovie.title() != null && !updatedMovie.title().trim().isEmpty() ? updatedMovie.title() : existingMovie.title(),
            updatedMovie.type() != null && !updatedMovie.type().trim().isEmpty() ? updatedMovie.type() : existingMovie.type(),
            updatedMovie.overview() != null && !updatedMovie.overview().trim().isEmpty() ? updatedMovie.overview() : existingMovie.overview(),
            updatedMovie.poster() != null && !updatedMovie.poster().trim().isEmpty() ? updatedMovie.poster() : existingMovie.poster(),
            updatedMovie.lposter() != null && !updatedMovie.lposter().trim().isEmpty() ? updatedMovie.lposter() : existingMovie.lposter(),
            updatedMovie.rent() != null && !updatedMovie.rent().trim().isEmpty() ? updatedMovie.rent() : existingMovie.rent(),
            updatedMovie.purchase() != null && !updatedMovie.purchase().trim().isEmpty() ? updatedMovie.purchase() : existingMovie.purchase(),
            updatedMovie.featured() != null && !updatedMovie.featured().trim().isEmpty() ? updatedMovie.featured() : existingMovie.featured()
        );
        return movieRepository.save(newMovie);
    }

    public void deleteMovieById(String id) throws Exception {
        // Validate if the movie exists
        Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(() -> new Exception("Movie with id " + id + " is not found."));

        // Delete the movie
        movieRepository.delete(existingMovie);
    }
}

//                  (updatedMovie.title() == null || updatedMovie.title().trim().isEmpty()) ? existingMovie.title() : updatedMovie.title(),
//                 (updatedMovie.type() == null || updatedMovie.type().trim().isEmpty()) ? existingMovie.type() : updatedMovie.type(),
//                 (updatedMovie.overview() == null || updatedMovie.overview().trim().isEmpty()) ? existingMovie.overview() : updatedMovie.overview(),
//                 (updatedMovie.poster() == null || updatedMovie.poster().trim().isEmpty()) ? existingMovie.poster() : updatedMovie.poster(),
//                 (updatedMovie.lposter() == null || updatedMovie.lposter().trim().isEmpty()) ? existingMovie.lposter() : updatedMovie.lposter(),
//                 (updatedMovie.rent() == null || updatedMovie.rent().trim().isEmpty()) ? existingMovie.rent() : updatedMovie.rent(),
//                 (updatedMovie.purchase() == null || updatedMovie.purchase().trim().isEmpty()) ? existingMovie.purchase() : updatedMovie.purchase(),
//                 (updatedMovie.featured() == null || updatedMovie.featured().trim().isEmpty()) ? existingMovie.featured() : updatedMovie.featured()