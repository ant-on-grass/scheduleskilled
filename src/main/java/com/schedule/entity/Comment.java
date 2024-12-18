package com.schedule.entity;


import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity // jpa 사용시 필요!
@Table(name="comment") // jpa 사용시 필요!
public class Comment extends CommonlyColumn{

    @Id // @Entity 즉, jpa를 사용할 땐, 테이블에 고유 식별자를 꼭 이처럼 지칭을 해줘야한다!
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 1씩 늘면서, 데이터 값을 저장!
    private Long id;

    @Column(columnDefinition = "longtext",nullable = false) // 컬럼들마다의 설정을 바꿔줄 수 있다. 해당 과정은 필수는 아니다.
    private String comment;

    @ManyToOne // TODO 1:다 에서 다인 경우를 지칭
    // TODO 해당 어너테이션으로 연관관계를 알려주고, 이처럼 한쪽만 쓴 경우 - 단방향 연관관계를 가진다? 라고 한다.
    @JoinColumn(name = "user_id") //TODO 어느 컬럼과 fk - pk 연결이 되는지 알려주면서, 테이블에 해당 이름으로 컬럼이 생성된다!
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
     * 유지보수 차원에서 만들어 본 메서드
     *
     * @param comment
     */
    public static Comment createComment(String comment,User user,Schedule schedule) { // static 필요! //TODO 다음 과제에서 많이 이용해보자!

        return new Comment(comment,user,schedule);
    }
}
