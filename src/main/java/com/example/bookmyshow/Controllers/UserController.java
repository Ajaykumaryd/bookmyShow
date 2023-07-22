package com.example.bookmyshow.Controllers;

import com.example.bookmyshow.Dtos.RequestDto.AddUserDto;
import com.example.bookmyshow.Dtos.ResponseDtos.UserResponseDto;
import com.example.bookmyshow.Models.User;
import com.example.bookmyshow.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody AddUserDto user){
        try{
            String result = userService.addUser(user);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    //Get oldest User Object by age
    @GetMapping("/getOlderUser")
    public ResponseEntity<UserResponseDto> getOldestUser(){
        try{
            UserResponseDto userResponseDto = userService.getOldestUser();
            userResponseDto.setStatusCode("200");
            userResponseDto.setStatusMessage("SUCCESS");
            return new ResponseEntity<>(userResponseDto,HttpStatus.CREATED);
        }catch (Exception e){
            UserResponseDto responseDto = new UserResponseDto();
            responseDto.setStatusCode("500");
            responseDto.setStatusMessage("Failure");
            return new ResponseEntity<>(responseDto,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findUsersGreaterThanAAge")
    public List<User> getAllUsers(@RequestParam("age")Integer age){
        return userService.getAllUserGreaterThan(age);
    }
}
