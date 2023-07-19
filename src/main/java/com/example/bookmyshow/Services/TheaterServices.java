package com.example.bookmyshow.Services;

import com.example.bookmyshow.Dtos.RequestDto.TheaterEntryDto;
import com.example.bookmyshow.Models.Theater;
import com.example.bookmyshow.Repository.TheaterRepository;
import com.example.bookmyshow.Transformers.TheaterTransformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TheaterServices {
    @Autowired
    private TheaterRepository theaterRepository;

    public String addTheater(TheaterEntryDto theaterEntryDto){

        //Entity that saves into the db
        //Convert the entryDto --> Entity and then save it
        Theater theater = TheaterTransformers.convertDtoToEntity(theaterEntryDto);
        theaterRepository.save(theater);

        return "Theater Added succesfully";
    }
}
