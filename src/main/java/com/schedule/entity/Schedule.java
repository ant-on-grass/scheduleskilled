package com.schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name="schedule")
@Getter
public class Schedule extends CommonlyColumn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "longtext")
    private String contents;

    @ManyToOne
    @JoinColumn(name="user_id")
    //TODO 유저 클래스의 id와 schedule 컬럼에 user_id 이 만들어져 연관관계를 가진다.
    private User user;

//
//    @ManyToOne
//    @JoinColumn(name = "member_id")
//    private Member member;

    public Schedule(User user,String title, String contents) {
        this.user = user;
        this.title = title;
        this.contents = contents;
    }

    public Schedule() {

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
    //    private LocalDateTime fixDate;
//    private LocalDateTime flexDate;
}

//작성 유저명, 할일 제목, 할일 내용, 작성일, 수정일