package com.example.bookmyshow.Models;
import com.example.bookmyshow.Enums.Genre;
import com.example.bookmyshow.Enums.Language;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name="movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String movieName;

    private double duration;

    @Column(scale = 2)
    private double rating;

    private Date releaseDate;

    @Enumerated(EnumType.STRING)
    private Genre genre;


    @Enumerated(EnumType.STRING)
    private Language language;

}
