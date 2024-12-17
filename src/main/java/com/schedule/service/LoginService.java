package com.schedule.service;

import com.schedule.config.PasswordEncoder;
import com.schedule.dto.LoginRequestDto;
import com.schedule.entity.User;
import com.schedule.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Boolean login(LoginRequestDto dto) {

        Optional<User> optinalUserbyEmail = userRepository.findByEmail(dto.getEmail());

        optinalUserbyEmail.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"이메일 혹은 비밀번호가 틀립니다."));

        User user = optinalUserbyEmail.get();

        // 비밀번호 검증

        String encodePassword = passwordEncoder.encode(dto.getPassword());

        if(!passwordEncoder.matches(dto.getPassword(),user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"이메일 혹은 비밀번호가 틀립니다.") ; // 위는 findBy로 검색하는 것이라, NOT_FOUND를 했고,
            // 이 경우는 UNAUTHORIZED 로 상태 메세지를 주는 게 맞는 거 같아서 변경
            // 근데 이러면 사용자가 이메일이 틀린지 비밀번호가 틀린지를 알 수 있게끔 힌트를 준거라 고민...
        }
        return true;
    }
}
