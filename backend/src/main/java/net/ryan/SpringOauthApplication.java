package net.ryan;

import net.ryan.config.RsaKeyProps;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProps.class)
@SpringBootApplication
public class SpringOauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringOauthApplication.class, args);
    }

}
