package com.schedule.repository;


import com.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;


public interface Repository extends JpaRepository<Schedule,Long> {

    // 2차 시도 // 이러면 새로운 저장 데이터가 생성... // save 때문에
    // 2차 시도 // update가 안되어 , 이를 이해결하기위한 시도 중 두번째
//    default Schedule findByIdModify(Long id,ScheduleRequestDto dto) {
//        Optional<Schedule> OptionalSchedule = findById(id);
//
//        Schedule schedule = OptionalSchedule.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 게시물을 찾을 수 없습니다."));
//
//        schedule = save(new Schedule(dto.getUserName(),dto.getTitle(),dto.getContents()));
//
//        return schedule;
//    }
}
