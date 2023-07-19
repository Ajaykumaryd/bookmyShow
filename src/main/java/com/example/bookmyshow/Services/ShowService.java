package com.example.bookmyshow.Services;

import com.example.bookmyshow.Dtos.RequestDto.AddShowDto;
import com.example.bookmyshow.Exception.MovieNotFound;
import com.example.bookmyshow.Exception.TheaterNotFound;
import com.example.bookmyshow.Models.Movie;
import com.example.bookmyshow.Models.Show;
import com.example.bookmyshow.Models.Theater;
import com.example.bookmyshow.Repository.MovieRepository;
import com.example.bookmyshow.Repository.ShowRepository;
import com.example.bookmyshow.Repository.TheaterRepository;
import com.example.bookmyshow.Transformers.ShowTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShowService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private ShowRepository showRepository;

    public String addShow(AddShowDto showDto)throws TheaterNotFound, MovieNotFound {

        Show show = ShowTransformer.convertDtoToEntity(showDto);
        //Set the movie and theater entity
        Optional<Movie> movieOptional = movieRepository.findById(showDto.getMovieId());

        if(!movieOptional.isPresent()){
            throw new MovieNotFound("Movie is not found");
        }
        Optional<Theater> theaterOptional = theaterRepository.findById(showDto.getTheaterId());

        if(!theaterOptional.isPresent()){
            throw new TheaterNotFound("Theater is not found");
        }

        Movie movie = movieOptional.get();
        Theater theater = theaterOptional.get();


        //Setting the foreign
        show.setMovie(movie);
        show.setTheater(theater);

        show = showRepository.save(show);

        movie.getShowList().add(show);
        movieRepository.save(movie);

        theater.getShowList().add(show);

        theaterRepository.save(theater);

        return "Show has been added and showId is "+show.getId();
    }
}
