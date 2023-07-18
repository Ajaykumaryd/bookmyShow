package com.example.bookmyshow.Services;

import com.example.bookmyshow.DTOs.RequestDto.MovieEntryDto;
import com.example.bookmyshow.Models.Movie;
import com.example.bookmyshow.Repository.MovieRepository;
import com.example.bookmyshow.Transformers.MovieTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

     @Autowired
     private MovieRepository movieRepository;
    public String addMovie(MovieEntryDto movieEntryDto) {

        Movie movie = MovieTransformer.convertDtoToEntity(movieEntryDto);
        movieRepository.save(movie);
        return "Movie added successfully";

    }
}
