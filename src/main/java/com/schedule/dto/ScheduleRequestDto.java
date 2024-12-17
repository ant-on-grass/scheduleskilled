package com.schedule.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleRequestDto {

//    @NotNull
//    private Long user_id; //TODO

    @NotNull
    private String title;

    @NotNull
    private String contents;

    public ScheduleRequestDto() {
    }
}
