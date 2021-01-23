package com.leo.zu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author leo-zu
 * @create 2020-10-03 8:32
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class AnalysisMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(AnalysisMain8001.class, args);
    }
}
