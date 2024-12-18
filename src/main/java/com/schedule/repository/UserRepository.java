package com.schedule.repository;


import com.schedule.entity.Schedule;
import com.schedule.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email); // TODO 쿼리 메서드 - 이전에 있던, 메서드를 쿼리처럼 쓸 수 있게끔 해준다! 매우 유용하나, 공부가 더 필요하다.

    Optional<User> findByUserName(String userName);

}
