package com.schedule.controller;


import com.schedule.dto.CommentRequestDto;
import com.schedule.dto.CommentResponseDto;
import com.schedule.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("schedules/{scheduleId}/comments")
@Slf4j
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> createCommet(@PathVariable("scheduleId") Long scheduleId, // @PathVariable("scheduleId") 지정하자!
                                                           @Validated @RequestBody CommentRequestDto dto) {

        return new ResponseEntity<>(commentService.createCommet(dto,scheduleId), HttpStatus.CREATED);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> findByIdComment(@PathVariable("commentId") Long commentId) { // @PathVariable("commentId") 지정하자!


        return new ResponseEntity<>(commentService.findByIdComment(commentId),HttpStatus.OK);
    }

    @PatchMapping("/{comment_id}")
    public ResponseEntity<CommentResponseDto> modifyByIdComment(@PathVariable("commentId") Long commentId, // @PathVariable("commentId") 지정하자!
                                                                @Validated @RequestBody CommentRequestDto dto) {


        return new ResponseEntity<>(commentService.modifyByIdComment(dto,commentId),HttpStatus.OK);
    }

    @DeleteMapping("/{comment_id}")
    public ResponseEntity<Void> DeleteByIdComment(@PathVariable("commentId") Long commentId) { // @PathVariable("commentId") 지정하자!

        commentService.DeleteByIdComment(commentId);

        return ResponseEntity.noContent().build();
    }

}
