package com.tommy.deploymentdemo.demo.iobound;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PostCacheService {

    private final PostRepository postRepository;
    private Page<Post> firstPostPage;

    public PostCacheService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Scheduled(cron = "* * * * * *")
    public void updateFirstPostPage() {
        PageRequest pageRequest = PageRequest.of(0, 20, Sort.by("id").descending());
        firstPostPage = postRepository.findAll(pageRequest);
    }

    public Page<Post> getFirstPostPage() {
        return firstPostPage;
    }
}
