package com.community.dev.service.post;

import com.community.dev.domain.post.Post;
import com.community.dev.domain.post.PostRepo;
import com.community.dev.web.dto.PostResponseDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepo postRepo;
    private final PasswordEncoder passwordEncoder;

    public PostService(PostRepo postRepo, PasswordEncoder passwordEncoder) {
        this.postRepo = postRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public void createPost(Post post) {
        String encodedPassword = passwordEncoder.encode(post.getPassword());
        postRepo.save(post.createPost(post.getWriter(),
                post.getTitle(), post.getContents(), encodedPassword));
    }

    public PostResponseDto getPost(Long postId) {
        return PostResponseDto.from(exist(postId));
    }

    public List<PostResponseDto> getAllPosts() {
        List<Post> posts = postRepo.findAll();
        return posts.stream().map(PostResponseDto::from).collect(Collectors.toList());
    }

    @Transactional
    public boolean updatePost(Post updatedPost, Long postId)  {
        Post existingPost = exist(postId);
        if (passwordEncoder.matches(updatedPost.getPassword(), existingPost.getPassword())) {
            existingPost.updatePost(updatedPost.getTitle(), updatedPost.getContents());
            return true;
        }
        return false;
    }

    @Transactional
    public boolean deletePost(Post updatedPost, Long postId) {
        Post existingPost = exist(postId);
        if (passwordEncoder.matches(updatedPost.getPassword(), existingPost.getPassword())) {
            postRepo.deleteById(postId);
            return true;
        }
        return false;
    }

    public Post exist(Long id) {
        return postRepo.findById(id).orElseThrow(() ->
            new EntityNotFoundException("게시글이 없습니다."));
    }
}
