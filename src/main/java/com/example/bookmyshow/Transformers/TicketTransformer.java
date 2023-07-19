package com.example.bookmyshow.Transformers;

import com.example.bookmyshow.DTOs.ResponseDto.TicketResponseDto;
import com.example.bookmyshow.Models.Show;
import com.example.bookmyshow.Models.Ticket;

public class TicketTransformer {
   public static TicketResponseDto createTicketResponseDto(Show show, Ticket ticket){

        TicketResponseDto ticketResponseDto=TicketResponseDto.builder().
                bookedSeats(ticket.getBookedSeats())
                .location(show.getTheater().getLocation())
                .movieName(show.getMovie().getMovieName())
                .showDate(show.getDate())
                .showTime(show.getTime())
                .totalPrice(ticket.getTotalTicketsPrice())
                .theaterName(show.getTheater().getName())
                .build();
     return  ticketResponseDto;
    }
}
