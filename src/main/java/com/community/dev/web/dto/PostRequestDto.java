package com.community.dev.web.dto;


import com.community.dev.domain.post.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostRequestDto {
    private String writer;
    private String title;
    private String contents;
    private String password;

    public PostRequestDto() {}

    @Builder
    public PostRequestDto(String writer, String title, String contents, String password) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.password = password;
    }


    public Post toEntity() {
        return Post.builder()
            .writer(writer)
            .title(title)
            .contents(contents)
            .password(password)
            .build();
    }
}
