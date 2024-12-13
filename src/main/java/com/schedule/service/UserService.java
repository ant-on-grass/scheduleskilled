package com.schedule.service;

import com.schedule.dto.UserRequestDto;
import com.schedule.dto.UserResponseDto;
import com.schedule.entity.User;
import com.schedule.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService {

    // private final ScheduleRepository scheduleRepository; // 나중에 인증 인가 후에 사용
    private final UserRepository usereRepository;

    public UserResponseDto createUser(UserRequestDto dto) {

        User user = usereRepository.save(new User(dto.getUserName(), dto.getEmail(),dto.getPassword()));

        return new UserResponseDto(user.getUserName(), user.getEmail(),user.getFixdate(),user.getFlexdate());
    }

    public UserResponseDto findByIdUser(Long id) {

        Optional<User> optionalUserById = usereRepository.findById(id);

        optionalUserById.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"해당 유저를 찾을 수 없습니다."));

        User user = optionalUserById.get();

        return new UserResponseDto(user.getUserName(), user.getEmail(),user.getFixdate(),user.getFlexdate());
    }

    @Transactional
    public UserResponseDto modifyByIdUser(Long id, UserRequestDto dto) {

        Optional<User> optionalUserById = usereRepository.findById(id);

        optionalUserById.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"해당 유저를 찾을 수 없습니다."));

        User user = optionalUserById.get();

        user.setUserName(dto.getUserName());
        user.setEmail(dto.getEmail());

        return new UserResponseDto(user.getUserName(), user.getEmail(),user.getFixdate(),user.getFlexdate());
    }

    public Void deleteUser(Long id) {

        usereRepository.deleteById(id);

        return null;
    }
}
