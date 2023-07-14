package com.example.bookmyshow.Services;

import com.example.bookmyshow.DTOs.RequestDto.AddShowDto;
import com.example.bookmyshow.Exceptions.MovieNotFound;
import com.example.bookmyshow.Exceptions.TheaterNotFound;
import com.example.bookmyshow.Models.Movie;
import com.example.bookmyshow.Models.Show;
import com.example.bookmyshow.Models.Theater;
import com.example.bookmyshow.Repository.MovieRepository;
import com.example.bookmyshow.Repository.ShowRepository;
import com.example.bookmyshow.Repository.TheaterRepository;
import com.example.bookmyshow.Transformers.MovieTransformer;
import com.example.bookmyshow.Transformers.ShowTransformer;
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

        Show show = ShowTransformer.convertDtoToEntity(addShowDto);

        Optional<Movie>  optionalMovie= movieRepository.findById(addShowDto.getMovieId());
        if(optionalMovie.isEmpty()){
           throw new MovieNotFound("Movie not found");
        }

        Optional<Theater> theaterOptional = theaterRepository.findById(addShowDto.getTheaterId());

        if(!theaterOptional.isPresent()){
            throw new TheaterNotFound("Theater is not found");
        }

        Movie movie=optionalMovie.get();
        Theater theater=theaterOptional.get();

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
