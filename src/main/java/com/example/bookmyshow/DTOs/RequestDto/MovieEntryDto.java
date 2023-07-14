package com.example.bookmyshow.DTOs.RequestDto;

import com.example.bookmyshow.Enums.Genre;
import com.example.bookmyshow.Enums.Language;
import lombok.Data;

import java.util.Date;

@Data
public class MovieEntryDto {
    private String movieName;
    private double duration;
    private double rating;
    private Date releaseDate;
    private Genre genre;
    private Language language;
}
