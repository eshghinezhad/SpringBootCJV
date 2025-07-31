package Seneca.CJV.MovieListingBackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Document(collection = "Movies")
public record Movie(
    @Id String id,
     @NotBlank(message = "Title is required")
    @Size(min = 3, max = 50, message = "Title must be between 3 and 50 characters")
    String title,
    @NotBlank(message = "Type is required")
    String type,
    @NotBlank(message = "Overview is required")
    @Size(min = 10, max = 500, message = "Overview must be between 10 and 500 characters")
    String overview,
    @NotBlank(message = "Small poster is required")
    String poster,
    @NotBlank(message = "Large poster is required")
    String lposter,
    @NotBlank(message = "Rental price is required")
    String rent,
    @NotBlank(message = "Purchase price is required")
    String purchase,
    String featured
) {}
