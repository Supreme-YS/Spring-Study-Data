package com.mbappe.book.springboot.web.dto;


import com.mbappe.book.springboot.domain.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
//절대로 entity클래스를 Request Response클래스로 사용을 하면 안된다.
public class PostsSaveRequestsDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestsDto(String title,String content,String author){
        this.title=title;
        this.content=content;
        this.author=author;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
