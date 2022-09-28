package com.community.dev.service.post;

import com.community.dev.domain.post.Post;
import com.community.dev.domain.post.PostRepo;
import com.community.dev.web.dto.PostRequestDto;
import com.community.dev.web.dto.PostResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepo postRepo;

    public PostService(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    public void createPost(PostRequestDto requestDto) {
        postRepo.save(requestDto.toEntity());
    }

    @Transactional
    public void deletePost(Long id) {
        isExist(id);
        postRepo.deleteById(id);
    }

    public List<PostResponseDto> getPost(Long id) {
        isExist(id);
        return postRepo.findById(id).stream().map(PostResponseDto::from)
            .collect(Collectors.toList());
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
