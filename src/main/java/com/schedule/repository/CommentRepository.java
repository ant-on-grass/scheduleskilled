package com.schedule.repository;

import com.schedule.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> { // 대망의 JPA
    //TODO extends JpaRepository 를 씀으로 jpa를 쓸 수 있게끔 된다. - 타입 매개변수로는 연결할 엔터티와 식별자의 타입을 명시한다!

}
