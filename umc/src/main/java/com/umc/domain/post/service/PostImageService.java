package com.umc.domain.post.service;

import com.umc.domain.post.entity.Post;
import com.umc.domain.post.entity.PostImage;
import com.umc.domain.post.repository.PostImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostImageService {
    private final PostImageRepository postImageRepository;
    private static String savePath = ""; // TODO

    public static String createRandomFilename(String original){
        // 저장용 파일명 생성 (랜덤 문자열을 앞에 붙임)
        return UUID.randomUUID().toString() + "_" + original;
    }

    public List<PostImage> save(MultipartFile[] imageFiles, Post post){
        List<PostImage> postImages = new ArrayList<>();
        Arrays.stream(imageFiles).forEach(image -> {
            // 1. PostImage 엔티티 생성
            postImages.add(PostImage.builder()
                    .originalFilename(image.getOriginalFilename())
                    .storedFilename(PostImageService.createRandomFilename(image.getOriginalFilename()))
                    .post(post)
                    .build());
            // 2. 파일 저장
            try {
                saveToDevice(image);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        // 3. 엔티티 저장
        postImageRepository.saveAll(postImages);
        return postImages;
    }

    public static void saveToDevice(MultipartFile image) throws IOException {
        image.transferTo(new File(PostImageService.savePath));
    }

    public static void saveToDevice(MultipartFile image, String path) throws IOException {
        image.transferTo(new File(path));
    }

    public List<PostImage> update(MultipartFile[] imageFiles, Post post) {
        deleteImagesOnPost(post);
        return save(imageFiles, post);
    }

    public void deleteImagesOnPost(Post post){
        List<PostImage> postImages = postImageRepository.findAllByPostId(post.getId());
        postImageRepository.deleteAll(postImages);
    }
}
