package com.umc.domain.comment.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentRequestDTO {
    private String content;
}
