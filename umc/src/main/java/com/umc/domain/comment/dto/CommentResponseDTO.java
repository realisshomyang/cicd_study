package com.umc.domain.comment.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentResponseDTO {
    private Long id;
    private String writerNickname;
    private String content;
}
