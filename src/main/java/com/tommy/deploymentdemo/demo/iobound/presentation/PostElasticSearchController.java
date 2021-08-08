package com.tommy.deploymentdemo.demo.iobound.presentation;

import com.tommy.deploymentdemo.demo.iobound.domain.Post;
import com.tommy.deploymentdemo.demo.iobound.domain.PostRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostElasticSearchController {

    private final PostRepository postRepository;

    public PostElasticSearchController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/search")
    public ResponseEntity<List<Post>> findPostsByContent(@RequestParam String content) {
        List<Post> posts = postRepository.findByContent(content);
        return ResponseEntity.ok(posts);
    }
}
