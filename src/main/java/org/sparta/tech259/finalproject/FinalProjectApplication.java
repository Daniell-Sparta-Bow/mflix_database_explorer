package org.sparta.tech259.finalproject;

import org.sparta.tech259.finalproject.repositories.MovieEntityRepository;
import org.sparta.tech259.finalproject.service.MovieService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FinalProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinalProjectApplication.class, args);
    }


}
