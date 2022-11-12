package com.community.dev.service.post;

import com.community.dev.domain.post.Post;
import com.community.dev.domain.post.PostRepo;
import com.community.dev.web.dto.PostRequestDto;
import com.community.dev.web.dto.PostResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepo postRepo;
    private final PasswordEncoder passwordEncoder;

    public PostService(PostRepo postRepo, PasswordEncoder passwordEncoder) {
        this.postRepo = postRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public void createPost(PostRequestDto requestDto) {
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
        postRepo.save(requestDto.createPost(requestDto.getWriter(),
            requestDto.getTitle(), requestDto.getContents(), encodedPassword));
    }

    @Transactional
    public void deletePost(Long id) {
        isExist(id);
        postRepo.deleteById(id);
    }

    public PostResponseDto getPost(Long postId) {
        return PostResponseDto.from(isExist(postId));
    }

    public List<PostResponseDto> getAllPosts() {
        List<Post> posts = postRepo.findAll();
        return posts.stream().map(PostResponseDto::from).collect(Collectors.toList());
    }

    @Transactional
    public void updatePost(PostRequestDto requestDto, Long postId) {
        Post post = isExist(postId);
        post.updatePost(requestDto.getTitle(), requestDto.getContents());
    }

    public Post isExist(Long id) {
        return postRepo.findById(id).orElseThrow(() ->
            new EntityNotFoundException("게시글이 없습니다."));
    }
}
