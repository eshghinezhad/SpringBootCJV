package Seneca.CJV.MovieListingBackend.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import Seneca.CJV.MovieListingBackend.model.User;

public interface UserRepository extends MongoRepository<User, String>{
    User findByEmail(String email);
    boolean existsByEmail(String email);
}
