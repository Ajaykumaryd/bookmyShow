package com.example.bookmyshow.Services;

import com.example.bookmyshow.DTOs.RequestDto.AddUserDto;
import com.example.bookmyshow.Models.User;
import com.example.bookmyshow.Repository.UserRepository;
import com.example.bookmyshow.Transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String add(AddUserDto user) {

        User useNew= UserTransformer.convertDtoToEntity(user);
        userRepository.save(useNew);
        return "User Saved Successfully";
    }
}
