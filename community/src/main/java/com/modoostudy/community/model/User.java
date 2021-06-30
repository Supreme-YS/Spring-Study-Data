package com.modoostudy.community.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity // 구성한 모든 데이터베이스에 매핑시키기 위한 Annotation
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long userId;
    private String userPw;
    private String nickname;
    private String category;
    private String region;
    private String geMail;
    private String userImage; // Image URL
}
