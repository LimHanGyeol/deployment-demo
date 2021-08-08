package com.tommy.deploymentdemo.demo.iobound.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

//@Entity
@Document(indexName = "post")
public class Post {

//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String id;

    private String content;

    protected Post() {
    }

    public Post(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
