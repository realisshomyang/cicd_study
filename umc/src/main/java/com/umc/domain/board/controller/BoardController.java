package com.umc.domain.board.controller;

import com.umc.common.response.ApiResponse;
import com.umc.domain.board.dto.BoardRequestDTO;
import com.umc.domain.board.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/board")
@RestController
public class BoardController {
    private final BoardService boardService;

    @CrossOrigin
    @Operation(summary = "게시판 생성 API")
    @PostMapping("/")
    public ApiResponse<Object> createBoard(@Valid @RequestBody BoardRequestDTO boardRequestDTO){
        return boardService.createBoard(boardRequestDTO);
    }

    @CrossOrigin
    @Operation(summary = "게시판 게시글 조회 API")
    @GetMapping("/{boardId}")
    public ApiResponse<Object> readBoard(@PathVariable String boardId){
        return boardService.readBoardPosts(boardId);
    }

    @CrossOrigin
    @Operation(summary = "게시판 삭제 API")
    @DeleteMapping("/{boardId}")
    public ApiResponse<Object> deleteBoard(@PathVariable String boardId){
        return boardService.delete(boardId);
    }

    @CrossOrigin
    @Operation(summary = "게시판 수정 API")
    @PutMapping("/{boardId}")
    public ApiResponse<Object> updateBoard(@PathVariable String boardId,
                                           @Valid @RequestBody BoardRequestDTO boardUpdateDTO){
        return boardService.update(boardId, boardUpdateDTO);
    }
}
