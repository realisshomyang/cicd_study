package com.umc.domain.post.repository;

import com.umc.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByBoardId(Long aLong);

    @Query("SELECT p FROM Post p JOIN LikePost lp ON p.id = lp.post.id WHERE lp.member.id = :memberId ORDER BY lp.createdAt DESC")
    List<Post> findAllLikedPostsByMemberIdOrderByLikedTime(Long memberId);
}
