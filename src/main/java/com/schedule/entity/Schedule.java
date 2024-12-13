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
    private String userName;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "longtext")
    private String contents;

    public Schedule(String userName, String title, String contents) {
        this.userName = userName;
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