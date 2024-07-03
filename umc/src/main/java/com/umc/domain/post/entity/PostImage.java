package com.umc.domain.post.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@Setter
@NoArgsConstructor
@Entity
@Table(name= "post_image")
@AllArgsConstructor
public class PostImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private String originalFilename;

    private String storedFilename;

    public void setPost(Post post){
        post.getImages().add(this);
        this.post = post;
    }
}
