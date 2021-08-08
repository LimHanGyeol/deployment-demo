package com.tommy.deploymentdemo.demo.iobound.domain;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//public interface PostRepository extends JpaRepository<Post, Long> {
@Repository
public interface PostRepository extends ElasticsearchRepository<Post, String> {

//    List<Post> findByContentContains(String content);
    List<Post> findByContent(String content);
}
