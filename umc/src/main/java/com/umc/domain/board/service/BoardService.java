package com.umc.domain.board.service;

import com.umc.common.response.ApiResponse;
import com.umc.common.response.status.SuccessCode;
import com.umc.domain.board.dto.BoardRequestDTO;
import com.umc.domain.board.entity.Board;
import com.umc.domain.board.repository.BoardRepository;
import com.umc.domain.post.dto.SimplePostResponseDTO;
import com.umc.domain.post.entity.Post;
import com.umc.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final PostRepository postRepository;

    public ApiResponse<Object> createBoard(BoardRequestDTO boardRequestDTO) {
        Board board = Board.builder()
                .title(boardRequestDTO.getTitle())
                .build();

        boardRepository.save(board);
        return ApiResponse.of(SuccessCode._OK, "저장되었습니다");
    }

    @Transactional
    public ApiResponse<Object> readBoardPosts(String boardId) {
        List<Post> posts = postRepository.findAllByBoardId(Long.getLong(boardId));

        List<SimplePostResponseDTO> postDTOs = posts.stream().map(post ->
            SimplePostResponseDTO.builder()
                    .boardId(Long.getLong(boardId))
                    .writerNickname(post.getWriter().getNickname())
                    .title(post.getTitle())
                    .createdAt(post.getCreatedAt())
                    .modifiedAt(post.getModifiedAt())
                    .build()
        ).toList();

        return ApiResponse.of(SuccessCode._OK, postDTOs);
    }

    public ApiResponse<Object> delete(String boardId) {
        Board target = boardRepository.findById(Long.getLong(boardId)).orElseThrow();
        boardRepository.delete(target);

        return ApiResponse.of(SuccessCode._OK, "삭제되었습니다");
    }

    public ApiResponse<Object> update(String boardId, BoardRequestDTO boardUpdateDTO) {
        Board target = boardRepository.findById(Long.getLong(boardId)).orElseThrow();
        target.setTitle(boardUpdateDTO.getTitle());
        boardRepository.save(target);

        return ApiResponse.of(SuccessCode._OK, "수정되었습니다");
    }
}
