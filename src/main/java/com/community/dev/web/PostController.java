package com.community.dev.web;

import com.community.dev.domain.post.Post;
import com.community.dev.domain.post.PostRepo;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posts/")
public class PostController {

    private final PostRepo postRepo;

    @Autowired
    public PostController(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    @GetMapping("write")
    public String showSignUpForm(Post post) {
        return "post-add";
    }

    @GetMapping("list")
    public String showUpdateForm(Model model) {
        model.addAttribute("posts", postRepo.findAll());
        return "post-index";
    }

    @PostMapping("write")
    public String writePost(@Valid Post post, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "post-add";
        }

        postRepo.save(post);
        return "redirect:list";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Post post = postRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + id));
        model.addAttribute("post", post);
        return "post-update";
    }

    @PostMapping("update/{id}")
    public String updatePost(@PathVariable("id") long id, @Valid Post post, BindingResult result,
        Model model) {
        if (result.hasErrors()) {
            post.setId(id);
            return "post-update";
        }

        postRepo.save(post);
        model.addAttribute("posts", postRepo.findAll());
        return "post-index";
    }

    @GetMapping("delete/{id}")
    public String deletePost(@PathVariable("id") long id, Model model) {
        Post post = postRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + id));
        postRepo.delete(post);
        model.addAttribute("posts", postRepo.findAll());
        return "post-index";
    }
}