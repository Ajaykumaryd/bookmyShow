package com.example.bookmyshow.Controllers;

import com.example.bookmyshow.DTOs.RequestDto.MovieEntryDto;
import com.example.bookmyshow.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    MovieService movieService;



    @PostMapping("/add")
    public String addMovie(@RequestBody MovieEntryDto movieEntryDto){
        return movieService.addMovie(movieEntryDto);
    }
}
