package com.schedule.service;

import com.schedule.dto.CommentRequestDto;
import com.schedule.dto.CommentResponseDto;
import com.schedule.entity.Comment;
import com.schedule.entity.Schedule;
import com.schedule.entity.User;
import com.schedule.repository.CommentRepository;
import com.schedule.repository.ScheduleRepository;
import com.schedule.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static com.schedule.entity.Comment.createByRequestDtoAtComment; // TODO 이런식으로 된다!

@Service
@AllArgsConstructor
public class CommentService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public CommentResponseDto createCommet(CommentRequestDto dto, Long scheduleId) {

        if(userRepository.findByUserName(dto.getUserName()) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"본인 계정에 이름을 입력해주세요.");
        }

        Result result = findByDtoAndByScheduleId(dto, scheduleId);

        Comment byRequestDtoAtComment =  createByRequestDtoAtComment(dto.getComment(), result.user(), result.schedule()); // TODO 신기!

        commentRepository.save(byRequestDtoAtComment);

        return new CommentResponseDto(byRequestDtoAtComment.getComment());
    }


    public CommentResponseDto findByIdComment(Long commentId) {

        Optional<Comment> optionalFindById = commentRepository.findById(commentId);

        optionalFindById.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));

        Comment comment = optionalFindById.get();

        return new CommentResponseDto(comment.getComment());
    }

    @Transactional
    public CommentResponseDto modifyByIdComment(CommentRequestDto dto, Long commentId) {

        Optional<Comment> optionalFindById = commentRepository.findById(commentId);

        optionalFindById.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));

        Comment comment = optionalFindById.get();

        comment.setComment(dto.getComment());

        return new CommentResponseDto(comment.getComment());
    }


    public void DeleteByIdComment(Long commentId) {

        commentRepository.deleteById(commentId);

    }


    private record Result(User user, Schedule schedule) {
    }

    /**
     * scheduleId 로 ScheduleRepository 의 findById 사용, schedule 객체 찾기
     * CommentRequestDto 에 dto.getUserName 로, UserRepository 의 findByUserName 사용, user 객체 찾기
     *
     * @param dto
     * @param scheduleId
     * @return 데이터베이스에 저장된 user 객체와 schedule 객체를 반환
     */
    private Result findByDtoAndByScheduleId(CommentRequestDto dto, Long scheduleId) {

        // 해당 과정은 사실 로그인 인증하고 들어온 것이라 확인할 필요가 없음
        Optional<User> findByUserName = userRepository.findByUserName(dto.getUserName());
        findByUserName.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        User user = findByUserName.get();

        // 해당 과정도 controller 에서 pathvaild 를 통해 오기 때문에 의미가 없을 듯?
        Optional<Schedule> findByIdSchedule = scheduleRepository.findById(scheduleId);
        findByIdSchedule.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Schedule schedule = findByIdSchedule.get();

        Result result = new Result(user, schedule);
        return result;
    }

}
