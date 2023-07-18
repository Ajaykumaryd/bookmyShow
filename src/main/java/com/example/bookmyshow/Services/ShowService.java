package com.example.bookmyshow.Services;

import com.example.bookmyshow.DTOs.RequestDto.AddShowDto;
import com.example.bookmyshow.DTOs.RequestDto.ShowSeatsDto;
import com.example.bookmyshow.Enums.SeatType;
import com.example.bookmyshow.Exceptions.MovieNotFound;
import com.example.bookmyshow.Exceptions.ShowNotFound;
import com.example.bookmyshow.Exceptions.TheaterNotFound;
import com.example.bookmyshow.Models.*;
import com.example.bookmyshow.Repository.MovieRepository;
import com.example.bookmyshow.Repository.ShowRepository;
import com.example.bookmyshow.Repository.TheaterRepository;
import com.example.bookmyshow.Transformers.MovieTransformer;
import com.example.bookmyshow.Transformers.ShowTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public String associateShowSeats(ShowSeatsDto showSeatsDto) throws ShowNotFound {

        Optional<Show> optionalShow = showRepository.findById(showSeatsDto.getShowId());
        //Validation check
        if(!optionalShow.isPresent()){
            throw new ShowNotFound("Show Id incorrect!!");
        }
        //Valid Show Now
        Show show = optionalShow.get();
        //We need to theaterSeats
        Theater theater = show.getTheater();
        List<TheaterSeat> theaterSeatList = theater.getTheaterSeatList();
        List<ShowSeat> showSeatList = show.getShowSeatList();
        for(TheaterSeat theaterSeat : theaterSeatList){
            ShowSeat showSeat = new ShowSeat();
            showSeat.setSeatNo(theaterSeat.getSeatNo());
            showSeat.setSeatType(theaterSeat.getSeatType());

            if(showSeat.getSeatType().equals(SeatType.CLASSIC))
                showSeat.setPrice(showSeatsDto.getPriceForClassicSeats());
            else
                showSeat.setPrice(showSeatsDto.getPriceForPremiumSeats());
            //Foreign key mapping
            showSeat.setShow(show);
            showSeat.setAvailable(true);
            showSeat.setFoodAttached(false);
            showSeatList.add(showSeat);
        }
        showRepository.save(show);
        //Child will automatically get saved.....
        return "Show seats has been successfully added";
    }
}
