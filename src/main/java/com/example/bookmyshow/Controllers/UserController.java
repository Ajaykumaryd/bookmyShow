package com.example.bookmyshow.Controllers;


import com.example.bookmyshow.DTOs.RequestDto.AddUserDto;
import com.example.bookmyshow.DTOs.ResponseDto.UserResponseDto;
import com.example.bookmyshow.Models.User;
import com.example.bookmyshow.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;



    @PostMapping("/add")
    public String adduser(@RequestBody AddUserDto user) {
        try {
            String result = userService.add(user);
            return result;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    //Get oldest User Object by age
    @GetMapping("/getOlderUser")
    public UserResponseDto getOldest(){

     try{
         UserResponseDto userResponseDto= userService.getoldest();
         userResponseDto.setStatusCode("200");
         userResponseDto.setStatusMessage("SUCCESS");
         return userResponseDto;

     }catch (Exception e){
         UserResponseDto responseDto = new UserResponseDto();
         responseDto.setStatusCode("500");
         responseDto.setStatusMessage("Failure");
         return responseDto;
     }


    }
    @GetMapping("/findUsersGreaterThanAAge")
    public List<User> getAllUsers(@RequestParam("age") Integer age){
        return userService.getAllUserGreaterThan(age);
    }


    }













