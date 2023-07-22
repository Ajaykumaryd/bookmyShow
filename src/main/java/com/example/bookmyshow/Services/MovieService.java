package com.example.bookmyshow.Services;

import com.example.bookmyshow.Dtos.RequestDto.MovieEntryDto;
import com.example.bookmyshow.Exception.MovieAlreadyPresentWithSameNameAndLanguage;
import com.example.bookmyshow.Exception.MovieNotFound;
import com.example.bookmyshow.Models.Movie;
import com.example.bookmyshow.Repository.MovieRepository;
import com.example.bookmyshow.Transformers.MovieTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public String addMovie(MovieEntryDto movieEntryDto) throws MovieAlreadyPresentWithSameNameAndLanguage {


        Optional<Movie> movieOptional= Optional.ofNullable(movieRepository.findByMovieName(movieEntryDto.getMovieName()));
        if(movieOptional.isPresent()){
            throw new MovieAlreadyPresentWithSameNameAndLanguage("Movie is Already present by same name");
        }
//        if(movieRepository.findByMovieName(movieEntryDto.getMovieName()) != null) {
//            if(movieRepository.findByMovieName(movieEntryDto.getMovieName()).getLanguage().equals(movieEntryDto.getLanguage())){
//                throw new MovieAlreadyPresentWithSameNameAndLanguage("Movie is Already present by same name");
//            }
//        }

        Movie movie = MovieTransformer.convertDtoToEntity(movieEntryDto);
        movieRepository.save(movie);
        return "Movie added successfully";

    }
}
