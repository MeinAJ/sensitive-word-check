package com.geega.bsc.word.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Jun.An3
 * @date 2022/07/20
 */
@SpringBootApplication
@ComponentScan(value = {"com.geega"})
public class WordApplication {

    public static void main(String[] args) {
        SpringApplication.run(WordApplication.class, args);
    }

}
