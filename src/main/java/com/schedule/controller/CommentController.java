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

    private final CommentService commentService; //DI

    @PostMapping // comment 등록
    public ResponseEntity<CommentResponseDto> createCommet(@PathVariable("scheduleId") Long schedulesId,
                                                           @Validated @RequestBody CommentRequestDto dto,
                                                           HttpServletRequest request) {
        // 해당 매서드의 설명은 밑에
        String email = getEmailBySession(request);

        return new ResponseEntity<>(commentService.createCommet(dto,email,schedulesId), HttpStatus.CREATED);
    }
    // comment 조회
    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> findByIdComment(@PathVariable("commentId") Long commentId) { // @PathVariable("commentId") 지정하자!


        return new ResponseEntity<>(commentService.findByIdComment(commentId),HttpStatus.OK);
    }

    // comment 수정
    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> modifyByIdComment(@PathVariable("commentId") Long commentId, // @PathVariable("commentId") 지정하자!
                                                                @Validated @RequestBody CommentRequestDto dto,
                                                                HttpServletRequest request) {

        String email = getEmailBySession(request);

        return new ResponseEntity<>(commentService.modifyByIdComment(dto,commentId,email),HttpStatus.OK);
    }

    // comment 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> DeleteByIdComment(@PathVariable("commentId") Long commentId,
                                                  HttpServletRequest request) { // @PathVariable("commentId") 지정하자!

        String email = getEmailBySession(request);

        commentService.DeleteByIdComment(commentId,email);

        return ResponseEntity.noContent().build();
    }

    /**
     *  HttpServletRequest를 통해 HttpSession을 받아, email value를 뽑아네는 매서드
     *
     * @param request
     * @return email 값
     */
    private String getEmailBySession(HttpServletRequest request) {

        HttpSession session = request.getSession();
        Map<String, String> sessionData = (Map<String, String>)session.getAttribute("sessionID");

        //Map<String, String> emailBySessionData = (Map<String, String>) sessionData; // 해당 주석들은 저의 자바 문법의 부족을 느끼게 해주는 부분....
        //Collection<String> emailCollection = emailBySessionData.values(); //TODO 자바 문법 공부가 필요... collection

        //List.of(sessionData.values()).get()

        String email = sessionData.get("email");

        //String email = emailCollection.toString();

        return email;
    }

}
