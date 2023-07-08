package com.example.bookmyshow.Transformers;

import com.example.bookmyshow.DTOs.RequestDto.AddUserDto;
import com.example.bookmyshow.Models.User;

public class UserTransformer {


    public static User convertDtoToEntity(AddUserDto userDto){

     User user=User.builder().age(userDto.getAge())
             .email(userDto.getEmailId())
             .mobNo(userDto.getMobNo()).name(userDto.getName()).build();
     return user;
    }



}
