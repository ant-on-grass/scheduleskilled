package com.schedule.controller;


import com.schedule.dto.CommentRequestDto;
import com.schedule.dto.CommentResponseDto;
import com.schedule.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("schedules/{scheduleId}/comments")
@Slf4j
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> createCommet(@PathVariable("scheduleId") Long schedulesId,
                                                           @Validated @RequestBody CommentRequestDto dto,
                                                           HttpServletRequest request) {

        String email = getEmailBySession(request);

        return new ResponseEntity<>(commentService.createCommet(dto,email,schedulesId), HttpStatus.CREATED);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> findByIdComment(@PathVariable("commentId") Long commentId) { // @PathVariable("commentId") 지정하자!


        return new ResponseEntity<>(commentService.findByIdComment(commentId),HttpStatus.OK);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> modifyByIdComment(@PathVariable("commentId") Long commentId, // @PathVariable("commentId") 지정하자!
                                                                @Validated @RequestBody CommentRequestDto dto,
                                                                HttpServletRequest request) {

        String email = getEmailBySession(request);

        return new ResponseEntity<>(commentService.modifyByIdComment(dto,commentId,email),HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> DeleteByIdComment(@PathVariable("commentId") Long commentId,
                                                  HttpServletRequest request) { // @PathVariable("commentId") 지정하자!

        String email = getEmailBySession(request);

        commentService.DeleteByIdComment(commentId,email);

        return ResponseEntity.noContent().build();
    }


    private String getEmailBySession(HttpServletRequest request) {

        HttpSession session = request.getSession();
        Map<String, String> sessionData = (Map<String, String>)session.getAttribute("sessionID");

        //Map<String, String> emailBySessionData = (Map<String, String>) sessionData;
        //Collection<String> emailCollection = emailBySessionData.values();

        //List.of(sessionData.values()).get()

        String email = sessionData.get("email");

        //String email = emailCollection.toString();

        return email;
    }

}
