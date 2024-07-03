package com.umc.domain.post.repository;

import com.umc.domain.post.entity.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikePostRepository extends JpaRepository<LikePost, Long> {

    boolean existsByPostIdAndMemberId(Long postId, Long memberId);

    int countByPostId(Long postId);
}
