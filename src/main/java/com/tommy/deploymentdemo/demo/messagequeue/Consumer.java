package com.tommy.deploymentdemo.demo.messagequeue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tommy.deploymentdemo.demo.iobound.domain.Post;
import com.tommy.deploymentdemo.demo.iobound.domain.PostRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    private final ObjectMapper objectMapper;
    private final PostRepository postRepository;

    public Consumer(ObjectMapper objectMapper, PostRepository postRepository) {
        this.objectMapper = objectMapper;
        this.postRepository = postRepository;
    }

    @RabbitListener(queues = "CREATE_POST_QUEUE")
    public void createPostQueueHandler(String message) throws JsonProcessingException {
        Post post = objectMapper.readValue(message, Post.class);
        postRepository.save(post);
    }
}
