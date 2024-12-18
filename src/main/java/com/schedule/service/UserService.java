package com.schedule.service;

import com.schedule.config.PasswordEncoder;
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

    private final UserRepository usereRepository;

    private final PasswordEncoder passwordEncoder;

    public UserResponseDto createUser(UserRequestDto dto) {

        //비밀번호 암호화
        String encodePassword = passwordEncoder.encode(dto.getPassword());

        // 생성
        User user = usereRepository.save(new User(dto.getUserName(), dto.getEmail(),encodePassword));

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

        // 비밀번호 검증
        // 해당 유저임은 이미 알지만, 대부분에 웹페이지에서 사용하듯 유저 정보 변경시 비밀번호 재입력
        if(!passwordEncoder.matches(dto.getPassword(),user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"비밀번호를 재 입력해주세요");
        }

        user.setUserName(dto.getUserName());
        //user.setEmail(dto.getEmail()); // 이메일로 로그인이라 이메일은 수정 불가하게

        return new UserResponseDto(user.getUserName(), user.getEmail(),user.getFixdate(),user.getFlexdate());
    }

    public Void deleteUser(Long id) { // 비밀번호도 입력하게 끔해야할 듯 < - 이건 안넣어도 될 듯

        usereRepository.deleteById(id);

        return null;
    }
}
