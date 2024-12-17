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

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> postSchedule(@Validated  @RequestBody ScheduleRequestDto dto
                                                            //HttpServletRequest request) {
    ) {
//        HttpSession session = request.getSession(false);
//
//        Object attribute = session.getAttribute(request.getRequestedSessionId());
//
//        Map<String, String> sessiondata = (Map<String, String>) attribute;
//
//        Collection<String> emailValues = sessiondata.values();
//
//        log.info("email 확인 : ", emailValues.toString());

        return new ResponseEntity<>(scheduleService.postSchedule(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable("id") Long id) {

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


}
