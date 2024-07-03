package com.umc.domain.comment.service;

import com.umc.common.jwt.SecurityUtil;
import com.umc.common.response.ApiResponse;
import com.umc.common.response.status.SuccessCode;
import com.umc.domain.comment.dto.CommentRequestDTO;
import com.umc.domain.comment.entity.Comment;
import com.umc.domain.comment.repository.CommentRepository;
import com.umc.domain.post.entity.Post;
import com.umc.domain.post.repository.PostRepository;
import com.umc.domain.user.entity.Member;
import com.umc.domain.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    public ApiResponse<List<Comment>> findByPostId(String postId) {
        List<Comment> comments = commentRepository.findAllByPostId(Long.getLong(postId));
        return ApiResponse.of(SuccessCode._OK, comments);
    }

    public ApiResponse<String> save(String postId, CommentRequestDTO commentRequest) {
        Member writer = memberRepository.findByEmail(
                SecurityUtil.getCurrentUserEmail()
            ).orElseThrow();
        Post post = postRepository.findById(Long.getLong(postId)).orElseThrow();
        Comment comment = Comment.builder()
                .content(commentRequest.getContent())
                .build();

        comment.setWriter(writer);
        comment.setPost(post);

        commentRepository.save(comment);
        return ApiResponse.of(SuccessCode._OK, "저장되었습니다");
    }

    public ApiResponse<Object> delete(String commentId) {
        Comment target = commentRepository.findById(Long.getLong(commentId)).orElseThrow();
        commentRepository.delete(target);
        return ApiResponse.of(SuccessCode._OK, "삭제되었습니다");
    }

    public ApiResponse<Object> update(String commentId, CommentRequestDTO commentRequestDTO) {
        Comment target = commentRepository.findById(Long.getLong(commentId)).orElseThrow();
        target.setContent(commentRequestDTO.getContent());
        commentRepository.save(target);
        return ApiResponse.of(SuccessCode._OK, "수정되었습니다");
    }
}
