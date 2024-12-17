package com.schedule.repository;


import com.schedule.entity.Schedule;
import com.schedule.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email); // 쿼리 메서드

    Optional<User> findByUserName(String userName);

}
