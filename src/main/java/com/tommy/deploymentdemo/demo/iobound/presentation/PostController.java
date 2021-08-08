package com.tommy.deploymentdemo.demo.iobound.presentation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tommy.deploymentdemo.demo.iobound.application.PostCacheService;
import com.tommy.deploymentdemo.demo.iobound.domain.Post;
import com.tommy.deploymentdemo.demo.messagequeue.Producer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class PostController {

    private static final int PAGE_SIZE = 20;

//    private final PostRepository postRepository;
    private final PostCacheService postCacheService;
    private final Producer producer;
    private final ObjectMapper objectMapper;

    public PostController(PostCacheService postCacheService, Producer producer, ObjectMapper objectMapper) {
        this.postCacheService = postCacheService;
        this.producer = producer;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestBody Post post) throws JsonProcessingException {
        String postToJson = objectMapper.writeValueAsString(post);
        producer.sendTo(postToJson);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/posts")
    public ResponseEntity<Page<Post>> findAll(@RequestParam(defaultValue = "1") Integer page) {
        // page는 배열과 같이 0부터 시작해서 -1을 내부적으로 해야 첫 페이지부터 시작한다.
        if (page.equals(1)) {
            Page<Post> cachedPostPage = postCacheService.getFirstPostPage();
            return ResponseEntity.ok(cachedPostPage);
        }
        PageRequest pageRequest = PageRequest.of(page - 1, PAGE_SIZE, Sort.by("id").descending());
//        Page<Post> posts = postRepository.findAll(pageRequest);
//        return ResponseEntity.ok(posts);
        return ResponseEntity.ok(Page.empty());
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> findPostById(@PathVariable("id") Long id) {
//        Post findPost = postRepository.findById(id).orElseThrow(IllegalArgumentException::new);
//        return ResponseEntity.ok(findPost);
        return ResponseEntity.ok().build();
    }

//    @GetMapping("/search")
//    public ResponseEntity<List<Post>> findPostsByContent(@RequestParam String content) {
//        List<Post> findPosts = postRepository.findByContentContains(content);
//        return ResponseEntity.ok(findPosts);
//        return ResponseEntity.ok().build();
//    }
}
