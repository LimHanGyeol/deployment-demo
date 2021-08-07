package com.tommy.deploymentdemo.demo.iobound;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class PostController {

    private static final int PAGE_SIZE = 20;

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post savedPost = postRepository.save(post);
        return ResponseEntity.ok(savedPost);
    }

    @GetMapping("/posts")
    public ResponseEntity<Page<Post>> findAll(@RequestParam(defaultValue = "1") Integer page) {
        // page는 배열과 같이 0부터 시작해서 -1을 내부적으로 해야 첫 페이지부터 시작한다.
        PageRequest pageRequest = PageRequest.of(page - 1, PAGE_SIZE, Sort.by("id").descending());
        Page<Post> posts = postRepository.findAll(pageRequest);
        return ResponseEntity.ok(posts);
    }

    // 글 번호를 조회

    // 글 내용으로 검색 -> 해당 내용이 포함된 모든 글글
}
