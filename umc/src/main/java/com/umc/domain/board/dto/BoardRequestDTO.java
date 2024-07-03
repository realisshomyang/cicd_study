package com.umc.domain.board.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardRequestDTO {
    @NotEmpty(message = "제목은 필수 입력값입니다.")
    private String title;
}
