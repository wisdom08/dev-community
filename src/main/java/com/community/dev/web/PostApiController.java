package com.community.dev.web;

import com.community.dev.service.post.PostService;
import com.community.dev.web.dto.PostPasswordDto;
import com.community.dev.web.dto.PostRequestDto;
import com.community.dev.web.dto.PostResponseDto;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/posts")
@RestController
public class PostApiController {

    private final PostService postService;

    public PostApiController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public void createPost(@RequestBody PostRequestDto requestDto) {
        postService.createPost(requestDto);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId, @RequestBody PostPasswordDto passwordDto)
        throws Exception {
        postService.deletePost(postId, passwordDto.getPassword());
    }

    @GetMapping("/{postId}")
    public PostResponseDto getPost(@PathVariable Long postId) {
        return postService.getPost(postId);
    }

    @GetMapping
    public List<PostResponseDto> getAllPosts() {
        return postService.getAllPosts();
    }

    @PutMapping("/{postId}")
    public void updatePost(@PathVariable Long postId, @RequestBody PostRequestDto requestDto)
        throws Exception {
        postService.updatePost(requestDto, postId);
    }
}
