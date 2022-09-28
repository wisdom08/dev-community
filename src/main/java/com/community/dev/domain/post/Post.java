package com.community.dev.domain.post;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String contents;

    protected Post() {}

    @Builder
    public Post(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public void updatePost(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
