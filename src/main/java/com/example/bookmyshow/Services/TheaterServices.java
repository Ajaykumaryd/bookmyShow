package com.example.bookmyshow.Services;

import com.example.bookmyshow.DTOs.RequestDto.TheaterEntryDto;
import com.example.bookmyshow.DTOs.RequestDto.TheaterSeatsEntryDto;
import com.example.bookmyshow.Models.Theater;
import com.example.bookmyshow.Repository.TheaterRepository;
import com.example.bookmyshow.Transformers.TheaterTransformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TheaterServices {

    @Autowired
    TheaterRepository theaterRepository;
    public String addTheater(TheaterEntryDto theaterEntryDto) {

//        Theater theater=new Theater();
//        theater.setName(theaterEntryDto.getName());
//        theater.setLocation(theaterEntryDto.getLocation());
//
//        theaterRepository.save(theater);

        //Entity that saves into the db
        //Convert the entryDto --> Entity and then save it
        Theater theater = TheaterTransformers.convertDtoToEntity(theaterEntryDto);
        theaterRepository.save(theater);
        return "theater added";
    }

    public String addTheaterSeats(TheaterSeatsEntryDto entryDto) {

        //We need to save the TheaterSeat Entity
        int columns = entryDto.getNoOfSeatsIn1Row();
        int noOfClassicSeats = entryDto.getNofOfClassicSeats();
        int noOfPremiumSeats = entryDto.getNoOfPremiumSeats();
        String location=entryDto.getLocation();
        Theater theater = theaterRepository.findByLocation(location);



    }
}
