package Seneca.CJV.MovieListingBackend.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import Seneca.CJV.MovieListingBackend.model.Movie;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {
    List<Movie> findByTypeContainingIgnoreCase(String type);
    List<Movie> findByTitleContainingIgnoreCase(String title);
    List<Movie> findByFeaturedContainingIgnoreCase(String featured);

}