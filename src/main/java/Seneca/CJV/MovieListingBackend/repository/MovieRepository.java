package Seneca.CJV.MovieListingBackend.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import Seneca.CJV.MovieListingBackend.model.Movie;

public interface MovieRepository extends MongoRepository<Movie, String> {
    List<Movie> findByTypeContainingIgnoreCase(String type);
    List<Movie> findByTitleContainingIgnoreCase(String title);
    List<Movie> findByFeaturedNotNullAndTypeContainingIgnoreCase(String type);

}