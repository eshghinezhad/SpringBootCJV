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
    // -------------------- Register a new User --------------------
    public User registerNewUser(User user) throws Exception {

        boolean exists = userRepository.existsByEmail(user.getEmail());
        if (exists) {
            throw new Exception("User with email: " + user.getEmail() + " already registered");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    // -------------------- Retrieves a specific User by ID--------------------
    public Optional<User> getUserById(String id) throws Exception {
       Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()) {
            throw new Exception("User not found with ID: " + id);
        }
        return user;
    }

    // -------------------- Authenticate a User --------------------
    public User loginUser(String email, String password) throws Exception {
        // if (email == null || email.trim().isEmpty()) {
        //     throw new RuntimeException("Email is required");
        // }
        
        // if (password == null || password.trim().isEmpty()) {
        //     throw new RuntimeException("Password is required");
        // }

        User existingUser = userRepository.findByEmail(email);
        if (existingUser == null) {
            throw new Exception("Invalid email or password");
        }

        if (!passwordEncoder.matches(password, existingUser.getPassword())) {
            throw new Exception("Invalid email or password");
        }

        return existingUser;
    }
}
