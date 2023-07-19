package com.example.bookmyshow.Services;

import com.example.bookmyshow.DTOs.RequestDto.TicketRequestDto;
import com.example.bookmyshow.DTOs.ResponseDto.TicketResponseDto;
import com.example.bookmyshow.Exceptions.NoUserFoundException;
import com.example.bookmyshow.Exceptions.ShowNotFound;
import com.example.bookmyshow.Models.Show;
import com.example.bookmyshow.Models.ShowSeat;
import com.example.bookmyshow.Models.Ticket;
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
    public TicketResponseDto book(TicketRequestDto ticketRequestDto) throws Exception {

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

        if(isValid==false){
            throw new Exception("Requested Seats entered are not available");
        }

        Ticket ticket=new Ticket();

        int totalPrice=calculateTotalPrice(show,ticketRequestDto.getRequestedSeats());
        ticket.setTotalTicketsPrice(totalPrice);


        String bookedSeats = convertListToString(ticketRequestDto.getRequestedSeats());
        ticket.setBookedSeats(bookedSeats);

        User user=userOptional.get();

        ticket.setUser(user);
        ticket.setShow(show);

        ticket=ticketRepository.save(ticket);

        user.getTicketList().add(ticket);
        userRepository.save(user);

        show.getTicketList().add(ticket);
        showRepository.save(show);


    }

    String convertListToString(List<String> seats){

        String result = "";
        for(String seatNo : seats){
            result = result + seatNo+", ";
        }
        return result;
    }

    private int calculateTotalPrice(Show show,List<String> requestedSeats) {
        int totalPrice=0;
        List<ShowSeat> showSeatList = show.getShowSeatList();
        for(ShowSeat showSeat:showSeatList){
            if(requestedSeats.contains(showSeat.getSeatNo())){
                totalPrice=totalPrice+showSeat.getPrice();
                showSeat.setAvailable(false);
            }
        }
        return totalPrice;
    }

    private boolean validateShowAvailability(Show show, List<String> requestedSeats){
     List<ShowSeat> showSeatList=show.getShowSeatList();
     for(ShowSeat showSeat:showSeatList){
         String seatNo = showSeat.getSeatNo();
          if(requestedSeats.contains(seatNo)){
              if(showSeat.isAvailable()==false);
              return false;
             }
      }
     return true;
    }









}
