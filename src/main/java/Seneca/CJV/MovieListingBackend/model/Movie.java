package Seneca.CJV.MovieListingBackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Movies")
public record Movie(
    @Id String id,
    String title,
    String type,
    String overview,
    String poster,
    String lposter,
    String rent,
    String purchase,
    String featured
) {}
