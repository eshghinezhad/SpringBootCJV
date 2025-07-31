package Seneca.CJV.MovieListingBackend.service;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Seneca.CJV.MovieListingBackend.model.User;
import Seneca.CJV.MovieListingBackend.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, org.springframework.security.crypto.password.PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String Email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(Email);
        if (!user.isPresent()){
            throw new UsernameNotFoundException("User with email: " + Email + " not found");
        }
        String userE = user.get().getEmail();
        String userP = user.get().getPassword();
        return new org.springframework.security.core.userdetails.User(userE, userP, new ArrayList<>());
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

    public Optional<User> getUserByEmail(String Email) throws Exception{
        Optional<User> user = userRepository.findByEmail(Email);
        if(!user.isPresent()) {
            throw new Exception("user with Email:"+Email+" not found");
        }
        return user;
    }
    // public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException{
    //     Optional<User> user = userRepository.findByEmail(email);
    //     if (!user.isPresent()){
    //         throw new UsernameNotFoundException("User with email: " +email+" not found");
    //     }
    //     String userE = user.get().getEmail();
    //     String userP = user.get().getPassword();
    //     return new org.springframework.security.core.userdetails.User(userE, userP, new ArrayList<>());
    // }
   
}
