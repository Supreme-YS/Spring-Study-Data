package com.supreme.board.domain.entity;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

// 수정할 때 마다 시간이 찍히는..
@Getter
@MappedSuperclass // 테이블로 매핑하는 것이 아니라, 자식 Entity에게 매핑정보를 상속하기 위한 어노테이션
@EntityListeners(AuditingEntityListener.class)
public class TimeEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

}
