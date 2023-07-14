package com.example.bookmyshow.Services;

import com.example.bookmyshow.DTOs.RequestDto.TheaterEntryDto;
import com.example.bookmyshow.DTOs.RequestDto.TheaterSeatsEntryDto;
import com.example.bookmyshow.Enums.SeatType;
import com.example.bookmyshow.Models.Theater;
import com.example.bookmyshow.Models.TheaterSeat;
import com.example.bookmyshow.Repository.TheaterRepository;
import com.example.bookmyshow.Transformers.TheaterTransformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterServices {

    @Autowired
    TheaterRepository theaterRepository;
    public String addTheater(TheaterEntryDto theaterEntryDto) {

//        Theater theater=new Theater();
//        theater.setName(theaterEntryDto.getName());
//        theater.setLocation(theaterEntryDto.getLocation());
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

        List<TheaterSeat> theaterSeatList=theater.getTheaterSeatList();

        int counter = 1;
        char ch = 'A';

        //this is done for the classic seats
        for(int count = 1;count<=noOfClassicSeats;count++){
            String seatNo = counter+"";
            seatNo = seatNo + ch;
            ch++;
            if((ch-'A')==columns){
                ch = 'A';
                counter++;
            }

            //setting up child class attributes
            TheaterSeat theaterSeat = new TheaterSeat();
            theaterSeat.setSeatNo(seatNo);
            theaterSeat.setTheater(theater); //storing parent info in child
            theaterSeat.setSeatType(SeatType.CLASSIC);

            //This is the bidirectional mapping...storing the child entity
            //in the parent entity
            theaterSeatList.add(theaterSeat);
        }

        //Lets to the same for the premium seats
        for(int count=1;count<=noOfPremiumSeats;count++){

            String seatNo = counter+"";
            seatNo = seatNo + ch;
            ch++;
            if((ch-'A')==columns){
                ch = 'A';
                counter++;
            }
            TheaterSeat theaterSeat = new TheaterSeat();
            theaterSeat.setTheater(theater);
            theaterSeat.setSeatType(SeatType.PREMIUM);
            theaterSeat.setSeatNo(seatNo);
            //This is the bidirectional mapping...storing the child entity
            //in the parent entity
            System.out.println("The seatNo is "+seatNo);
            theaterSeatList.add(theaterSeat);
        }

        //We just need to save the parent : theater Entity
        //child will automatically get saved bcz of bidirectional mapping
        theaterRepository.save(theater);
        return "Theater Seats have been successfully added";
    }
}
