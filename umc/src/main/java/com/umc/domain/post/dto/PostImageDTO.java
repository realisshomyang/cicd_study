package com.umc.domain.post.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostImageDTO {
    private String originalFilename;
    private String storedFilename;
}
