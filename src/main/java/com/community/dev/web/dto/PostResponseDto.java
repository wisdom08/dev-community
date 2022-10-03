package com.community.dev.web.dto;


import com.community.dev.domain.post.Post;
import lombok.Getter;

@Getter
public class PostResponseDto {

    private Long id;
    private String title;
    private String contents;

    protected PostResponseDto() {}

    private PostResponseDto(Long id, String title, String contents) {
        this.id = id;
        this.title = title;
        this.contents = contents;
    }

    public static PostResponseDto from(Post entity) {
        return new PostResponseDto(entity.getId(), entity.getTitle(), entity.getContents());
    }
}
