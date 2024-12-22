package rasrov.decskill.inditex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DecskillInditexApplication {

    public static void main(String[] args) {
        SpringApplication.run(DecskillInditexApplication.class, args);
    }

}
