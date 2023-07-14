package com.example.bookmyshow.DTOs.ResponseDto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {

    private String name;
    private int age;
    private String mobNo;
    private String statusCode;
    private String statusMessage;

}
