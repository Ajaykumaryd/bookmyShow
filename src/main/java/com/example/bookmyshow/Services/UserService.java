package com.example.bookmyshow.Services;

import com.example.bookmyshow.DTOs.RequestDto.AddUserDto;
import com.example.bookmyshow.DTOs.ResponseDto.UserResponseDto;
import com.example.bookmyshow.Exceptions.NoUserFoundException;
import com.example.bookmyshow.Models.User;
import com.example.bookmyshow.Repository.UserRepository;
import com.example.bookmyshow.Transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String add(AddUserDto user) {
        User useNew= UserTransformer.convertDtoToEntity(user);
        userRepository.save(useNew);
        return "User Saved Successfully";
    }

    public UserResponseDto getoldest() throws NoUserFoundException {
        List<User> list=userRepository.findAll();
        int maxAge=0;
        User userAns = null;
        for(User user:list){
            if(user.getAge()>maxAge){
                maxAge= user.getAge();
                userAns=user;
            }
        }
        if (userAns==null){
            throw new NoUserFoundException("User is not present");
        }
        UserResponseDto userResponseDto = UserTransformer.convertEntityToDto(userAns);
        return userResponseDto;
    }
}
