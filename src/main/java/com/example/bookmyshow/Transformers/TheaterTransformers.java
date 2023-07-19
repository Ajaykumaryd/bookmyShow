package com.example.bookmyshow.Transformers;

import com.example.bookmyshow.Dtos.RequestDto.TheaterEntryDto;
import com.example.bookmyshow.Models.Theater;

public class TheaterTransformers {
    public static Theater convertDtoToEntity(TheaterEntryDto theaterEntryDto){

        Theater theater = Theater.builder().location(theaterEntryDto.getLocation())
                .name(theaterEntryDto.getName()).build();

        return theater;
    }
}
