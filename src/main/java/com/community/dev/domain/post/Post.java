package com.community.dev.domain.post;

import com.community.dev.domain.reply.Reply;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private final List<Reply> replies = new ArrayList<>();

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
