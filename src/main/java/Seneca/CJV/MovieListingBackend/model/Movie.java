package Seneca.CJV.MovieListingBackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Movies")
public class Movie {
    @Id
    private Integer id;
    private String title;
    private String overview;
    private String poster;
    private String lposter;
    private String rent;
    private String purchase;
    private String featured;
 

    public Movie() {
        // No-args constructor required by Spring Data
    }

}
