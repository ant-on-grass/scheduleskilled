package com.schedule.controller;


import com.schedule.dto.ScheduleRequestDto;
import com.schedule.dto.ScheduleResponseDto;
import com.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> postSchedule(@Validated  @RequestBody ScheduleRequestDto dto) {

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
