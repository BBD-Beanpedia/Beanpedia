package net.ryan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringOauthApplication {
    private static final Logger log = LoggerFactory.getLogger(SpringOauthApplication.class);


    public static void main(String[] args) {
        log.debug("Test");
        SpringApplication.run(SpringOauthApplication.class, args);
    }

}
