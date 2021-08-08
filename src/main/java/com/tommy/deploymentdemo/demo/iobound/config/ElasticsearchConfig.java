package com.tommy.deploymentdemo.demo.iobound.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ElasticsearchConfig {

    @Value("#{'${spring.data.elasticsearch.hosts}'.split(',')}")
    private List<String> hosts;

    @Value("${spring.data.elasticsearch.port}")
    private int port;

    @Bean
    public RestHighLevelClient getRestClient() {
        List<HttpHost> restHosts = new ArrayList<>();
        for (String host : hosts) {
            restHosts.add(new HttpHost(host, port, "http"));
        }
        RestClientBuilder builder = RestClient.builder(
                restHosts.toArray(new HttpHost[restHosts.size()])
        );
        return new RestHighLevelClient(builder);
    }
}
