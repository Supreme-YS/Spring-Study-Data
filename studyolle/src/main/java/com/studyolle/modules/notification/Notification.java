package com.studyolle.modules.notification;

import com.studyolle.modules.account.Account;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
public class Notification {

    @Id @GeneratedValue
    private Long id;

    private String title;

    private String link;

    private String message;

    private boolean checked;

    // 계정 하나에 여러 개의 알람이 갈 수 있다.
    // 참조키 설정을 해준 것이다.
    // 알림은 자신의 정보(id, title, link, message, checked, createDateTime, notificationType)
    // 그리고 각 알림들은 계정에 대한 참조값 id 를 가진다.
    // account_id
    @ManyToOne
    private Account account;

    private LocalDateTime createdDateTime;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

}
