package com.iscolt.multipart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;

@SpringBootApplication
public class MultipartApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultipartApplication.class, args);
    }

}
