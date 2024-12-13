package com.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleRequestDto {

    private Long user_id; //TODO
    private String title;
    private String contents;

    public ScheduleRequestDto() {
    }
}
