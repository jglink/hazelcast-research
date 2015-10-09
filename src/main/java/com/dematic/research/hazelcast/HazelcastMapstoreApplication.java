package com.dematic.research.hazelcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@ImportResource({ "classpath:hazelcast-config.xml" })
public class HazelcastMapstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(HazelcastMapstoreApplication.class, args);
    }

}
