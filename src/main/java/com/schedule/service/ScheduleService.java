package com.schedule.service;

import com.schedule.dto.ScheduleRequestDto;
import com.schedule.dto.ScheduleResponseDto;
import com.schedule.entity.Schedule;
import com.schedule.repository.Repository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ScheduleService {

    private final Repository repository;

    public ScheduleResponseDto postSchedule(ScheduleRequestDto dto) {


        Schedule schedule = repository.save(new Schedule(dto.getUserName(), dto.getTitle(), dto.getContents()));

        return new ScheduleResponseDto(
                schedule.getUserName(),schedule.getTitle(),
                schedule.getContents(),schedule.getFixdate(),
                schedule.getFlexdate()
        );
    }

    public ScheduleResponseDto findById(Long id) {

        Optional<Schedule> scheduleById = repository.findById(id);

        scheduleById.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"해당 게시물을 찾을 수 없습니다." ));

        Schedule schedule = scheduleById.get();

        return new ScheduleResponseDto(
                schedule.getUserName(),schedule.getTitle(),
                schedule.getContents(),schedule.getFixdate(),
                schedule.getFlexdate()
        );
    }


    @Transactional // 매우 중요!!
//    해당 코드 실행 시, 영속성 컨텐츠?에 범위는 find 했을 때로 잡힘? //TODO 더 공부할 내용
//
//    쉽게 생각하자면, 쿼리가 2개이상 일 때, 트렌잭션해야한다?
//    트랜잭션널이, 영속성 컨텐츠의 범위를 해당 코드 안에서 쿼리로 잡아준다?
//    영속성 컨텐스츠 범위가 있다 - 생명주기 - > 범위를 벗어나면, 영속성 컨텐츠가 사라진다!?
    public ScheduleResponseDto modifyById(Long id, ScheduleRequestDto dto) {
//        1차 시도 // update가 안되어 , 이를 이해결하기위한 시도 중 하나

        Optional<Schedule> scheduleById = repository.findById(id);

        scheduleById.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"변경할 게시물을 찾을 수 없습니다." ));

        Schedule schedule = scheduleById.get();

        schedule.setTitle(dto.getTitle());
        schedule.setContents(dto.getContents());

        return new ScheduleResponseDto(
                schedule.getUserName(),schedule.getTitle(),
                schedule.getContents(),schedule.getFixdate(),
                schedule.getFlexdate()
        );

//         2차 시도 // update가 안되어 , 이를 이해결하기위한 시도 중 두번째
//
//
//        Schedule schedule = repository.findByIdModify(id, dto);
//
//        return new ScheduleResponseDto(
//                schedule.getUserName(),schedule.getTitle(),
//                schedule.getContents(),schedule.getFixdate(),
//                schedule.getFlexdate()
//        );
    }

    public Void DeleteById(Long id) {

        repository.deleteById(id);

        return null;
    }
}
