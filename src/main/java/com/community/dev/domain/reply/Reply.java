package com.community.dev.domain.reply;

import com.community.dev.domain.post.Post;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Entity
public class Reply {

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private Post post;

    private String writer;
    private String contents;

    protected Reply() {}

    public Reply(Post post, String writer, String contents) {
        this.post = post;
        this.writer = writer;
        this.contents = contents;
    }

    public static Reply createReply(Post post, String writer, String contents) {
        return new Reply(post, writer, contents);
    }
}
