package com.example.bookmyshow.Services;

import com.example.bookmyshow.DTOs.RequestDto.AddShowDto;
import com.example.bookmyshow.Exceptions.MovieNotFound;
import com.example.bookmyshow.Exceptions.TheaterNotFound;
import com.example.bookmyshow.Models.Movie;
import com.example.bookmyshow.Repository.MovieRepository;
import com.example.bookmyshow.Repository.ShowRepository;
import com.example.bookmyshow.Repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private MovieRepository movieRepository;
    public String add(AddShowDto addShowDto) throws MovieNotFound,TheaterNotFound {

        Optional<Movie>  optionalMovie= movieRepository.findById(addShowDto.getMovieId());
        if(optionalMovie.isEmpty()){
           throw new MovieNotFound("Movie not found");
        }
    }
}
