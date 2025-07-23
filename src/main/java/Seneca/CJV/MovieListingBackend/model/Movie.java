package Seneca.CJV.MovieListingBackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Movies")
public class Movie {
    @Id
    private String id;
    private String title;
    private String overview;
    private String poster;
    private String lposter;
    private double rent;
    private double purchase;
    private String featured;

}
