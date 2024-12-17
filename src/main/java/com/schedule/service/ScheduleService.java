package com.schedule.service;

import com.schedule.dto.ScheduleRequestDto;
import com.schedule.dto.ScheduleResponseDto;
import com.schedule.entity.Schedule;
import com.schedule.entity.User;
import com.schedule.repository.ScheduleRepository;
import com.schedule.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository usereRepository;

    @Transactional
    public ScheduleResponseDto postSchedule(ScheduleRequestDto dto) {

        Optional<User> optionalUser = usereRepository.findById(dto.getUser_id());
        // optionalUser.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"로그인 후 이용가능 합니다." ));
        User user = optionalUser.get();

        Schedule schedule = scheduleRepository.save(new Schedule(user,dto.getTitle(), dto.getContents())); // 새로만든다 - 세팅
        //TODO Schedule 생성시에 user의 정보를 담아주지 않으면 schedule 테이블의 user_id 가 널 값을 가지게 된다.
        // 연관성을 부여하고 대입을 안하면 말짱황 // 1번째 시도 requestDto에 user_id 값을 넣으려했지만, 생성자의 실행문을 어떻게 채워야할지 감이 안잡힘
        // 그래서 user 객체를 넘겨줌
        return new ScheduleResponseDto(
                user.getUserName(), schedule.getTitle(),
                schedule.getContents(),schedule.getFixdate(),
                schedule.getFlexdate()
        );
    }

    public ScheduleResponseDto findById(Long id) {

        Optional<Schedule> scheduleById = scheduleRepository.findById(id);

        scheduleById.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"해당 게시물을 찾을 수 없습니다." ));

        Schedule schedule = scheduleById.get();

        return new ScheduleResponseDto(
                schedule.getUser().getUserName(),schedule.getTitle(),
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

        Optional<Schedule> scheduleById = scheduleRepository.findById(id);

        scheduleById.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"변경할 게시물을 찾을 수 없습니다." ));

        Schedule schedule = scheduleById.get();

        schedule.setTitle(dto.getTitle());
        schedule.setContents(dto.getContents());

        return new ScheduleResponseDto(
                schedule.getUser().getUserName(),schedule.getTitle(),
                schedule.getContents(),schedule.getFixdate(),
                schedule.getFlexdate()
        );

//         2차 시도 // update가 안되어 , 이를 이해결하기위한 시도 중 두번째
//
//
//        Schedule schedule = repository.findByIdModify(id, dto);
//
//        return new ScheduleResponseDto(
//                schedule.getUser().getUserName(),schedule.getTitle(),
//                schedule.getContents(),schedule.getFixdate(),
//                schedule.getFlexdate()
//        );
    }

    public Void DeleteById(Long id) {

        scheduleRepository.deleteById(id);

        return null;
    }
}
