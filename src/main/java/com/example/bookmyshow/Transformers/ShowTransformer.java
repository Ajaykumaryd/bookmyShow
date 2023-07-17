package com.example.bookmyshow.Transformers;

import com.example.bookmyshow.DTOs.RequestDto.AddShowDto;
import com.example.bookmyshow.Models.Show;

public class ShowTransformer {
    public static Show convertDtoToEntity(AddShowDto addShowDto) {

        Show show = Show.builder().time(addShowDto.getShowStartTime())
                .date(addShowDto.getShowDate()).build();

        return show;

    }


}