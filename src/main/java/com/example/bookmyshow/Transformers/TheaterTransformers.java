package com.example.bookmyshow.Transformers;

import com.example.bookmyshow.DTOs.RequestDto.TheaterEntryDto;
import com.example.bookmyshow.Models.Theater;

public class TheaterTransformers {


    public static Theater convertDtoToEntity(TheaterEntryDto theaterEntryDto){

    Theater theater=Theater.builder().name(theaterEntryDto.getName()).
                     location(theaterEntryDto.getLocation()).build();
    return theater;
    }





}
