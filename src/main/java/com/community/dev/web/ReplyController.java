package com.community.dev.web;

import com.community.dev.domain.reply.Reply;
import com.community.dev.service.post.PostService;
import com.community.dev.service.reply.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/posts/{id}/replies/")
public class ReplyController {

    private final PostService postService;
    private final ReplyService replyService;

    @Autowired
    public ReplyController(PostService postService, ReplyService replyService) {
        this.postService = postService;
        this.replyService = replyService;
    }


    @PostMapping("/write")
    public String writeReply(@PathVariable("id") long id, @Valid Reply reply, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "reply-add";
        }
        replyService.createReply(id, reply);

        model.addAttribute("reply", reply);
        return "reply";
    }
}