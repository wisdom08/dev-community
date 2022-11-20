package com.community.dev.web;

import com.community.dev.domain.post.Post;
import com.community.dev.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;

@Controller
@RequestMapping("/posts/")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("write")
    public String showSignUpForm(Post post) {
        return "post-add";
    }

    @GetMapping("list")
    public String showUpdateForm(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "post-index";
    }

    @PostMapping("write")
    public String writePost(@Valid Post post, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "post-add";
        }
        postService.createPost(post);
        return "redirect:list";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("post", postService.getPost(id));
        return "post-update";
    }

    @GetMapping("delete/{id}")
    public String showDeleteForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("post", postService.getPost(id));
        return "post-delete";
    }

    @PostMapping("update/{id}")
    public String updatePost(@PathVariable("id") long id, @Valid Post post, BindingResult result,
        Model model) {
        if (result.hasErrors()) {
            post.setId(id);
            return "post-update";
        }

        if (postService.updatePost(post, id)) {
            model.addAttribute("posts", postService.getAllPosts());
            return "post-index";
        } else {
            model.addAttribute("post", postService.getPost(id));
            return "post-error";
        }
    }

    @PostMapping("delete/{id}")
    public String deletePost(@PathVariable("id") long id, @Valid Post post, BindingResult result,
                             Model model){
        if (result.hasErrors()) {
            post.setId(id);
            return "post-delete";
        }

        if (postService.deletePost(post, id)) {
            model.addAttribute("posts", postService.getAllPosts());
            return "post-index";
        } else {
            model.addAttribute("post", postService.getPost(id));
            return "post-error-for-delete";
        }
    }
}