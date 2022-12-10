package com.community.dev.service.reply;

import com.community.dev.domain.reply.Reply;
import com.community.dev.domain.reply.ReplyRepo;
import com.community.dev.service.post.PostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyService {

    private final ReplyRepo replyRepo;
    private final PostService postService;

    public ReplyService(ReplyRepo replyRepo, PostService postService) {
        this.replyRepo = replyRepo;
        this.postService = postService;
    }

    public void createReply(Long postId, Reply reply) {
        postService.getPost(postId);
        replyRepo.save(Reply.createReply(postService.exist(postId), reply.getWriter(), reply.getContents()));
    }

    public List<Reply> getReplies(long id) {
        return replyRepo.findByPostId(id);
    }
}
