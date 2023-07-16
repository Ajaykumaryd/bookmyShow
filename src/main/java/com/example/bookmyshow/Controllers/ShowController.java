package com.example.bookmyshow.Controllers;

import com.example.bookmyshow.DTOs.RequestDto.AddShowDto;
import com.example.bookmyshow.DTOs.RequestDto.ShowSeatsDto;
import com.example.bookmyshow.Repository.ShowRepository;
import com.example.bookmyshow.Services.ShowService;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/show")
public class ShowController {

     @Autowired
     private ShowService showService;


     @PostMapping("/add")
     public String addShow(@RequestBody AddShowDto addShowDto){
         try{
             return showService.add(addShowDto);
         }catch (Exception e){
             return e.getMessage();
         }
     }

     @PostMapping("/associate-seats")
     public String associateSeats(@RequestBody ShowSeatsDto showSeatsDto){

         try{
             return showService.associateShowSeats(showSeatsDto);
         }catch (Exception e){
             return e.getMessage();
         }

     }

}
