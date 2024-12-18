package com.schedule.entity;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // TODO 잘모르는 어노테인션
@EntityListeners(AuditingEntityListener.class) // TODO 잘모르는 어노테인션
public abstract class CommonlyColumn { // 컬럼 생성시 자동으로 작성일과 수정일을 만들어주는 친구
    //TODO 해당 코드는 과제 내에서 한번만 작성하면 되서, 다음에도 사용하면서 익혀야할 듯하다.

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime fixdate;

    @LastModifiedDate
    private LocalDateTime flexdate;
}


