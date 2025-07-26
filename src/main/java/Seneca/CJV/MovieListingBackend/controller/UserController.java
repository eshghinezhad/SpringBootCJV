package Seneca.CJV.MovieListingBackend.controller;

import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Seneca.CJV.MovieListingBackend.CustomizedResponse;
import Seneca.CJV.MovieListingBackend.model.User;
import Seneca.CJV.MovieListingBackend.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }

    @GetMapping("/{id}")
    // public ResponseEntity<User> getUser(@PathVariable("id") String id) {
    //     CustomizedResponse customizedResponse = null;
    //     try{
    //         customizedResponse = new CustomizedResponse(" Get User with id " + id , Collections.singletonList(userService.getUserById(id)));
    //     } catch (Exception e){
    //         customizedResponse = new CustomizedResponse(e.getMessage(), null);
    //         return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
    //     }
    //     return new ResponseEntity(customizedResponse, HttpStatus.OK);
    // }
        public ResponseEntity<CustomizedResponse<User>> getUserById(@PathVariable("id") String id) {
        CustomizedResponse<User> customizedResponse ;
        try{
            User user = userService.getUserById(id).orElseThrow(() -> new Exception("User not found"));
            customizedResponse = new CustomizedResponse<>(" Get User with id " + id , Collections.singletonList(user));
        } catch (Exception e){
            customizedResponse = new CustomizedResponse<>(e.getMessage(), null);
            return new ResponseEntity<>(customizedResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customizedResponse, HttpStatus.OK);
    }
}
