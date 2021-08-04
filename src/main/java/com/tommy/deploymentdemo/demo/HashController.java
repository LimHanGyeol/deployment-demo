package com.tommy.deploymentdemo.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HashController {

    @GetMapping("/hash/{id}")
    public String getDigest(@PathVariable("id") String id) {
        return id;
    }

    @GetMapping("/hello")
    public String hello() {
        return "github webhook deploy test";
    }
}
