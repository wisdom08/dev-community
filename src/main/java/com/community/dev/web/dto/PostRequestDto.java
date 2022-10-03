package com.community.dev.web.dto;


import com.community.dev.domain.post.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostRequestDto {
    private String title;
    private String contents;

    public PostRequestDto() {}

    @Builder
    public PostRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }


    public Post toEntity() {
        return Post.builder()
            .title(title)
            .contents(contents)
            .build();
    }
}
