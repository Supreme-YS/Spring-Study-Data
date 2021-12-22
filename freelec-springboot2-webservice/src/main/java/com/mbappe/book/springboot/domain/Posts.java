package com.mbappe.book.springboot.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
////////@Setter는 안만든다 .
@Entity
public class Posts extends BaseTimeEntity {
    @Id
    //생성 규칙을 만들어준다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    //기본값 이외에 추가적으로 기능을 넣을려고 할떄 @Column을 쓴다.
    @Column(length = 500,nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;

    private String author;


    //Setter대신에 Builder를 쓴다.
    @Builder
    public Posts(String title,String content,String author){
        this.title=title;
        this.content=content;
        this.author=author;
    }
    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
