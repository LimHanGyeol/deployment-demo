package com.tommy.deploymentdemo.demo.iobound;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @PostMapping("/api/post")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post savedPost = postRepository.save(post);
        return ResponseEntity.ok(savedPost);
    }

    @GetMapping("/api/posts")
    public ResponseEntity<List<Post>> getPosts() {
        List<Post> posts = postRepository.findAll();
        return ResponseEntity.ok(posts);
    }

    // 글 목록을 페이징하여 반환

    // 글 번호를 조회

    // 글 내용으로 검색 -> 해당 내용이 포함된 모든 글글
}
