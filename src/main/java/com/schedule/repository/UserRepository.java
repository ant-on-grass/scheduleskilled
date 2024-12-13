package com.schedule.repository;


import com.schedule.entity.Schedule;
import com.schedule.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {

}
