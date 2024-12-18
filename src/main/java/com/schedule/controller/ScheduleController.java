package com.schedule.controller;


import com.schedule.dto.ScheduleRequestDto;
import com.schedule.dto.ScheduleResponseDto;
import com.schedule.service.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService; //DI

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> postSchedule(@Validated  @RequestBody ScheduleRequestDto dto,
                                                            HttpServletRequest request) { // 로그인한 상태를 생각해 HttpServletRequest - > session을 받아 해당 과정이 되게끔 만듬
                                                                                            // 밑에 같은 내용은 생략
        // 해당 코드의 설명은 밑에
        String email = getEmailBySession(request);

        return new ResponseEntity<>(scheduleService.postSchedule(dto,email), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable("id") Long id) { // TODO @PathVariable("id")에서 id 지칭은 내생각엔 필수! 그리고 url에 쓴 것과 당연하게 같게 작성해야한다!

        return new ResponseEntity<>(scheduleService.findById(id),HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> modifyById(@PathVariable("id") Long id,
                                                          @Validated @RequestBody ScheduleRequestDto dto) {

        return new ResponseEntity<>(scheduleService.modifyById(id,dto),HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteById(@PathVariable("id") Long id) {

        return new ResponseEntity<>(scheduleService.DeleteById(id),HttpStatus.NO_CONTENT);

    }

    /**
     *  HttpServletRequest를 통해 HttpSession을 받아, email value를 뽑아네는 매서드
     *
     * @param request
     * @return email 값
     */
    private String getEmailBySession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        Object attribute = session.getAttribute("sessionID");

        Map<String, String> sessiondata = (Map<String, String>) attribute;

        String email = sessiondata.get("email");
        return email;
    }
}
