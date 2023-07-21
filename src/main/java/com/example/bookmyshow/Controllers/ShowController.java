package com.example.bookmyshow.Controllers;

import com.example.bookmyshow.Dtos.RequestDto.AddShowDto;
import com.example.bookmyshow.Dtos.RequestDto.ShowSeatsDto;
import com.example.bookmyshow.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    private ShowService showService;
    @PostMapping("/add")
    public String addShow(@RequestBody AddShowDto addShowDto){
        try{
            return showService.addShow(addShowDto);
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

    @GetMapping("/most-recommended-movie-name")
    public String getMovieName(AddShowDto addShowDto){
        return showService.getMovieName(addShowDto);
    }

}
