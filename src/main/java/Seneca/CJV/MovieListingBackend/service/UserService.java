package Seneca.CJV.MovieListingBackend.service;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Seneca.CJV.MovieListingBackend.model.User;
import Seneca.CJV.MovieListingBackend.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, org.springframework.security.crypto.password.PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerNewUser(User user) throws Exception {

        boolean exists = userRepository.existsByEmail(user.getEmail());
        if (exists) {
            throw new Exception("User with email: " + user.getEmail() + " already exists");
        }
// validate user data******************
        if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
            throw new Exception("First name is required");
        }
        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            throw new Exception("Last name is required");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new Exception("Email is required");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new Exception("Password is required");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
