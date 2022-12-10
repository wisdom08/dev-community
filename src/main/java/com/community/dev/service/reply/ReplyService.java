package com.community.dev.service.reply;

import com.community.dev.domain.reply.Reply;
import com.community.dev.domain.reply.ReplyRepo;
import com.community.dev.service.post.PostService;
import com.community.dev.web.dto.ReplyResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<ReplyResponseDto> getReplies(long id) {
        List<Reply> replies = replyRepo.findByPostId(id);
        return replies.stream().map(ReplyResponseDto::from).collect(Collectors.toList());
    }
}
