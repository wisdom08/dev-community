package com.community.dev.domain.post;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
public class Post {

    @Setter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String writer;
    private String title;

    private String contents;
    private String password;

    protected Post() {}

    @Builder
    public Post(String writer, String title, String contents, String password) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.password = password;
    }

    public void updatePost(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public static Post createPost(String writer, String title, String contents, String password) {
        return new Post(writer, title, contents, password);
    }
}
