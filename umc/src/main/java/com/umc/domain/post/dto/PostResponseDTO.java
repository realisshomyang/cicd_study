package com.umc.domain.post.dto;

import com.umc.domain.board.entity.Board;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class PostResponseDTO {
    private Long id;
    private String title;
    private Long boardId;
    private String writerNickname;
    private String content;
    private List<PostImageDTO> images;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
