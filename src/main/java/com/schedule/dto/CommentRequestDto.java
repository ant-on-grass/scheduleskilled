package com.schedule.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentRequestDto {

    @NotNull
    private String userName;

    @NotNull
    private String comment;

    public CommentRequestDto() { // TODO 역직렬화를 위해 꼭 필요!
    }
}
