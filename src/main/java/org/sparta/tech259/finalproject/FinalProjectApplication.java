package org.sparta.tech259.finalproject;


import org.bson.types.ObjectId;
import org.sparta.tech259.finalproject.model.repositories.CommentRepository;
import org.sparta.tech259.finalproject.service.CommentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FinalProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinalProjectApplication.class, args);
    }


    @Bean
    CommandLineRunner runner(CommentService commentService, CommentRepository commentRepository) {
        return args -> {
            //ObjectId movieId = new ObjectId("573a1390f29313caabcd4323");
            System.out.println(commentService.getCommentsByMovieId("573a1390f29313caabcd4323"));
            //System.out.println(commentService.getCommentsByMovieId("573a1390f29313caabcd4323"));
        };
    }
}
