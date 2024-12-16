package com.schedule.service;

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

    @Transactional
    public Boolean login(LoginRequestDto dto) {

        Optional<User> optinalUserbyEmail = userRepository.findByEmail(dto.getEmail());

        optinalUserbyEmail.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"이메일 혹은 비밀번호가 틀립니다."));

        User user = optinalUserbyEmail.get();

        if(!user.getPassword().equals(dto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"이메일 혹은 비밀번호가 틀립니다.") ;
        }
        return true;
    }
}
