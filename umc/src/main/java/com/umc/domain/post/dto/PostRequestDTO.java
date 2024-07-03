package com.umc.domain.post.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostRequestDTO {
    private String title;
    private String content;
}
