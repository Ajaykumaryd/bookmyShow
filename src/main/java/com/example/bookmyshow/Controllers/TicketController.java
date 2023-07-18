package com.example.bookmyshow.Controllers;

import com.example.bookmyshow.DTOs.RequestDto.TicketRequestDto;
import com.example.bookmyshow.DTOs.ResponseDto.TicketResponseDto;
import com.example.bookmyshow.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {


    @Autowired
    TicketService ticketService;


    @PostMapping("/book")
    public ResponseEntity<TicketRequestDto> bookTicket(@RequestBody TicketRequestDto ticketRequestDto){

     try{
         TicketResponseDto response=ticketService.book(ticketRequestDto);

     }catch (Exception e){

     }
    }
}
