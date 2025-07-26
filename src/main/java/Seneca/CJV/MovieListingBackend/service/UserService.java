package Seneca.CJV.MovieListingBackend.service;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Seneca.CJV.MovieListingBackend.model.User;
import Seneca.CJV.MovieListingBackend.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerNewUser(User user) {
        // Logic to save the user in the database
        return userRepository.save(user);
    }
    
    public Optional<User> getUserById(String id) throws Exception {
       Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()) {
            throw new Exception("User not found with ID: " + id);
        }
        return user;
    }
    
}
