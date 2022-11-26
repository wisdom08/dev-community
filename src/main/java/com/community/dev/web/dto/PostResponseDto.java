package com.community.dev.web.dto;


import com.community.dev.domain.post.Post;
import lombok.Getter;

@Getter
public class PostResponseDto {

    private Long id;
    private String title;
    private String contents;
    private String writer;
    private String password;

    protected PostResponseDto() {}

    private PostResponseDto(Long id, String title, String contents, String writer, String password) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.password = password;
    }

    public static PostResponseDto from(Post entity) {
        return new PostResponseDto(entity.getId(), entity.getTitle(), entity.getContents(), entity.getWriter(), entity.getPassword());
    }
}
