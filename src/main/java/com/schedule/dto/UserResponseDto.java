package com.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserResponseDto {


    private String userName;
    private String email;
    private LocalDateTime fixdate;
    private LocalDateTime flexdate;

}
