package com.community.dev.domain.reply;

import com.community.dev.domain.post.Post;
import com.community.dev.domain.post.PostRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class ReplyRepoTest {

    private Post post;

    @Autowired
    private ReplyRepo replyRepo;

    @Autowired
    private PostRepo postRepo;

    @BeforeEach
    void setUp() {
        post = Post.createPost("writer", "title,", "contents", "1234");

        postRepo.save(post);
        replyRepo.save(Reply.createReply(this.post, "replyWriter", "replyContents"));
        replyRepo.save(Reply.createReply(this.post, "replyWriter2", "replyContents2"));
    }

    @Test
    void 하나의_게시글에_작성된_댓글_개수_조회() {
        assertThat(replyRepo.findByPostId(post.getId()).size()).isEqualTo(2);
    }
}