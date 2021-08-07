package com.tommy.deploymentdemo.demo.iobound;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByContentContains(String content);
}
