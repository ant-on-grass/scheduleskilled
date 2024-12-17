package com.schedule.config;


import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {

    public String encode(String rawPassword) {

        return BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, rawPassword.toCharArray());

        // withDefaults 는 BCrypt 객체를 생성(초기화) 기본값으로 암호화 알고리즘 : VERSION_2A , slat : SecureRandom
        // hashToString 해당 메서드는 실행 될 때, hash 을 진행! - > 그리고 String(문자열)로 만드는 과정까지의 역할을 한다!

        // MIN_COST 암호화의 강도( 해싱 횟수 - min는 10번 )
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(), encodedPassword);
        return result.verified;

        // verifyer 검증을 위한 메서드
        // 원리 원본데이터 + 솔트 - > 해씽 -> 문자열 반환
        // 문자열이 된 암호화 비밀번호 데이터는 솔트의 원본 부분(비밀번호를 암호화하기전!)이 어디인지 알 수 있고,
        // 해당 솔트(원본)과 검증하고자 하는 비밀번호 를 같이 해씽하여, 암호화된 비밀번호랑 맞는지 검증!!

    }
}