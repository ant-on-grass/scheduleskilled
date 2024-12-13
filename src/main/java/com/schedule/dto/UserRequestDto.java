package com.schedule.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRequestDto {

    private String userName;
    private String email;

    public UserRequestDto() {
    }
}
