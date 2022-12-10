package com.community.dev.domain.reply;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepo extends JpaRepository<Reply, Long> {
    List<Reply> findByPostId(long postId);
}
