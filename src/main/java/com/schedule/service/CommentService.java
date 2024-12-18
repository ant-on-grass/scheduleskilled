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
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static com.schedule.entity.Comment.createComment; // TODO 이런식으로 된다!

@Slf4j
@Service
@AllArgsConstructor
public class CommentService {

    private final ScheduleRepository scheduleRepository; // DI
    private final UserRepository userRepository; // DI
    private final CommentRepository commentRepository; // DI

    @Transactional
    public CommentResponseDto createCommet(CommentRequestDto dto, String email , Long scheduleId) {

        // 이메일을 통해 유저 엔터티의 저장데이터에 접근하는 모습
        Optional<User> byEmail = userRepository.findByEmail(email);

        log.info("test");

        // 메서드의 설명은 밑에 있다
        User user = byEmail.get();

        if(!user.getUserName().equals(dto.getUserName())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"본인 계정에 이름을 입력해주세요.");
        }

        Optional<Schedule> findByIdSchedule = scheduleRepository.findById(scheduleId);
        findByIdSchedule.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Schedule schedule = findByIdSchedule.get();

        Comment createComment =  createComment(dto.getComment(),user,schedule); // TODO 신기! - 유지보수를 위해 필요!!

        commentRepository.save(createComment);

        return new CommentResponseDto(createComment.getComment());
    }

    @Transactional
    public CommentResponseDto findByIdComment(Long commentId) {

        Optional<Comment> optionalFindById = commentRepository.findById(commentId);

        optionalFindById.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));

        Comment comment = optionalFindById.get();

        return new CommentResponseDto(comment.getComment());
    }

    @Transactional
    public CommentResponseDto modifyByIdComment(CommentRequestDto dto, Long commentId ,String email ) {

        log.info("본인 계정임을 확인합니다.");

        Comment comment = selfCommentToCheck(commentId);

        if(!comment.getUser().getEmail().equals(email)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"본인 계정에 작성물만 수정할 수 있습니다.");
        }

        comment.setComment(dto.getComment());

        return new CommentResponseDto(comment.getComment());
    }

    @Transactional
    public void DeleteByIdComment(Long commentId,String email) {

        log.info("본인 계정임을 확인합니다.");

        Comment comment = selfCommentToCheck(commentId);

        if(!comment.getUser().getEmail().equals(email)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"본인 계정에 작성물만 삭제할 수 있습니다.");
        }

        commentRepository.deleteById(commentId);
    }

    /**
     * CommentRepository 에서 고유 식별 id를 가지고 comment 저장 객체를 뽑아내는 메서드
     *
     * @param commentId
     * @return comment 저장 객체
     */
    private Comment selfCommentToCheck(Long commentId) {
        Optional<Comment> optionalFindById = commentRepository.findById(commentId);

        optionalFindById.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));

        Comment comment = optionalFindById.get();
        return comment;
    }
}
