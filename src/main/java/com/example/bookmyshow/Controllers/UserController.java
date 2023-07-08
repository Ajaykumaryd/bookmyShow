package com.example.bookmyshow.Controllers;


import com.example.bookmyshow.DTOs.RequestDto.AddUserDto;
import com.example.bookmyshow.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;

    public String adduser(@RequestBody AddUserDto user){

    String result=userService.add(user);
    return result;
    }















}
