package com.schedule.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@NotNull
public class UserRequestDto {

    @Size(min = 2,max=10)
    private String userName;

    @Pattern(regexp = "\\w+@\\w+.\\w+(.\\w+)?")
    private String email;

    private String password;

    public UserRequestDto() {
    }
}
