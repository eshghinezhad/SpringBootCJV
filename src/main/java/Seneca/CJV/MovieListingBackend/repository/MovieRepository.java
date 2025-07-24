package Seneca.CJV.MovieListingBackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import Seneca.CJV.MovieListingBackend.model.Movie;

public interface MovieRepository extends MongoRepository<Movie, String> {
}