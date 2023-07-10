package com.example.bookmyshow.DTOs.RequestDto;


import lombok.Data;

@Data
public class AddUserDto {
    private String name;
    private Integer age;
    private String mobNo;
    private String email;

}
