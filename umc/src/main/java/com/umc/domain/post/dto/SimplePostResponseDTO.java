package com.umc.domain.post.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class SimplePostResponseDTO {
    private Long id;
    private String title;
    private Long boardId;
    private String writerNickname;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
