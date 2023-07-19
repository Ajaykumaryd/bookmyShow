package com.example.bookmyshow.Controllers;

import com.example.bookmyshow.Dtos.RequestDto.AddShowDto;
import com.example.bookmyshow.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
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
            return showService.addShow(addShowDto);
        }catch (Exception e){
            return e.getMessage();
        }

    }
}
