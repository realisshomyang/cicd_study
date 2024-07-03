package com.umc.domain.post.controller;

import com.umc.common.response.ApiResponse;
import com.umc.domain.post.dto.PostRequestDTO;
import com.umc.domain.post.dto.PostResponseDTO;
import com.umc.domain.post.dto.SimplePostResponseDTO;
import com.umc.domain.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/posts")
@RestController
public class PostController {

    private final PostService postService;

    @CrossOrigin
    @Operation(summary = "게시글 조회 API")
    @GetMapping
    public ApiResponse<List<SimplePostResponseDTO>> findPosts() {
        return postService.findAll();
    }

    @CrossOrigin
    @Operation(summary = "특정 게시글 조회 API")
    @GetMapping("/{postId}")
    public ApiResponse<PostResponseDTO> findPost(@PathVariable String postId){
        return postService.find(postId);
    }

    @CrossOrigin
    @Operation(summary = "특정 게시글 삭제 API")
    @DeleteMapping("/{postId}")
    public ApiResponse<String> deleteById(@PathVariable String postId){
        return postService.delete(postId);
    }

    @CrossOrigin
    @Operation(summary = "게시글 생성 API")
    @PostMapping
    public ApiResponse<String> postPost(@RequestParam(name = "boardId") String boardId,
                                        @ModelAttribute PostRequestDTO postRequestDto,
                                        @RequestParam(value = "images", required = false) MultipartFile[] images){
        return postService.save(boardId, postRequestDto, images);
    }

    @CrossOrigin
    @Operation(summary = "게시글 수정 API")
    @PutMapping("/{postId}")
    public ApiResponse<String> updatePost(@PathVariable String postId,
                                          @ModelAttribute PostRequestDTO postRequestDto,
                                          @RequestParam(value = "images", required = false) MultipartFile[] images){
        return postService.update(postId, postRequestDto, images);
    }

    @CrossOrigin
    @Operation(summary = "게시글 좋아요 API")
    @PostMapping("/{postId}/likes")
    public ApiResponse<String> likePost(@PathVariable String postId){
        return postService.hitLike(postId);
    }

    @CrossOrigin
    @Operation(summary = "게시글 좋아요 개수 조회 API")
    @GetMapping("/{postId}/likes")
    public ApiResponse<Integer> getLikes(@PathVariable String postId){
        return postService.getLikes(postId);
    }

    @CrossOrigin
    @Operation(summary = "사용자가 좋아요 누른 게시글 조회 API")
    @GetMapping("/liked")
    public ApiResponse<List<SimplePostResponseDTO>> getLikedPosts(){
        return postService.getLikedPostsByUser();
    }

    @CrossOrigin
    @Operation(summary = "게시글 좋아요 누름 여부 조회 API")
    @GetMapping("/{postId}/liked")
    public ApiResponse<Boolean> checkLike(@PathVariable String postId){
        return postService.checkLike(postId);
    }
}
