package com.example.bookmyshow.Services;

import com.example.bookmyshow.Dtos.RequestDto.MovieEntryDto;
import com.example.bookmyshow.Exception.MovieAlreadyPresentWithSameNameAndLanguage;
import com.example.bookmyshow.Exception.MovieNotFound;
import com.example.bookmyshow.Models.Movie;
import com.example.bookmyshow.Models.Show;
import com.example.bookmyshow.Models.Ticket;
import com.example.bookmyshow.Repository.MovieRepository;
import com.example.bookmyshow.Repository.ShowRepository;
import com.example.bookmyshow.Transformers.MovieTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ShowRepository showRepository;

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

    public Long totalCollection(Integer movieId) throws MovieNotFound{
     Optional<Movie> optionalMovie=movieRepository.findById(movieId);
     if(optionalMovie.isEmpty()){
         throw new MovieNotFound("Movie is not present with this Id"+movieId);
     }
     List<Show> showListOfMovie = showRepository.getAllShowsOfMovie(movieId);
     long amount = 0;
        for(Show show : showListOfMovie) {
            for(Ticket ticket : show.getTicketList()){
                amount += (long)ticket.getTotalTicketsPrice();
            }
        }
        return amount;
    }

}
