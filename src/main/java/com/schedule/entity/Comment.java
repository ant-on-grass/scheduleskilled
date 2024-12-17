package com.schedule.entity;


import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name="comment")
public class Comment extends CommonlyColumn{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "longtext",nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    private Comment(String comment,User user,Schedule schedule) {
        this.comment = comment;
        this.user = user;
        this.schedule = schedule;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Comment() {
    }


    /**
     * create construct method
     *
     * @param comment
     */
    public static Comment createByRequestDtoAtComment(String comment,User user,Schedule schedule) { // static 필요!

        return new Comment(comment,user,schedule);
    }
}
