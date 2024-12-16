package com.schedule.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NotNull
public class UserResponseDto {

    @Size(min = 2,max=10)
    private String userName;

    private String email;

    private LocalDateTime fixdate;

    private LocalDateTime flexdate;

}
