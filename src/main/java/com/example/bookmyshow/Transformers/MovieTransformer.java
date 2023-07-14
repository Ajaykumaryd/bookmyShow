package com.example.bookmyshow.Transformers;

import com.example.bookmyshow.DTOs.RequestDto.MovieEntryDto;
import com.example.bookmyshow.Models.Movie;

public class MovieTransformer {

    public static Movie convertDtoToEntity (MovieEntryDto movieEntryDto){
        Movie movie = Movie.builder().movieName(movieEntryDto.getMovieName())
                .duration(movieEntryDto.getDuration())
                .genre(movieEntryDto.getGenre())
                .language(movieEntryDto.getLanguage())
                .rating(movieEntryDto.getRating()).releaseDate(movieEntryDto.getReleaseDate())
                .build();
        return movie;
    }
}
