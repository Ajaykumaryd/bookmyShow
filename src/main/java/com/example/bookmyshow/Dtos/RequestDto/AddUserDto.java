package com.example.bookmyshow.Dtos.RequestDto;

import lombok.Data;

@Data
public class AddUserDto {
    private String name;
    private Integer age;
    private String mobNo;
    private String emailId;
}
