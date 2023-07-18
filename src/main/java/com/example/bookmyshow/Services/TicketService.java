package com.example.bookmyshow.Services;

import com.example.bookmyshow.DTOs.RequestDto.TicketRequestDto;
import com.example.bookmyshow.DTOs.ResponseDto.TicketResponseDto;
import com.example.bookmyshow.Exceptions.NoUserFoundException;
import com.example.bookmyshow.Exceptions.ShowNotFound;
import com.example.bookmyshow.Models.Show;
import com.example.bookmyshow.Models.ShowSeat;
import com.example.bookmyshow.Models.User;
import com.example.bookmyshow.Repository.ShowRepository;
import com.example.bookmyshow.Repository.TicketRepository;
import com.example.bookmyshow.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ShowRepository showRepository;
    public TicketResponseDto book(TicketRequestDto ticketRequestDto)throws ShowNotFound,NoUserFoundException {

        int userId=ticketRequestDto.getUserId();
        Optional<User> userOptional=userRepository.findById(userId);

        if(!userOptional.isPresent()){
            throw new NoUserFoundException("User is not found");
        }



        int showId=ticketRequestDto.getShowId();
        Optional<Show> showOptional=showRepository.findById(showId);

        if(!showOptional.isPresent()){
            throw new ShowNotFound("Show not found");
        }

        Show show=showOptional.get();

        boolean isValid= validateShowAvailability(show,ticketRequestDto.getRequestedSeats());
    }

    public boolean validateShowAvailability(Show show, List<String> requestedSeats){

     List<ShowSeat> showSeatList=show.getShowSeatList();

     for(ShowSeat showSeat:showSeatList){

     }

    }
}
