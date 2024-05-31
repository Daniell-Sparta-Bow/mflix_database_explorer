package org.sparta.tech259.finalproject.controller.rest;


import java.util.List;
import java.util.Optional;

import org.sparta.tech259.finalproject.model.entities.Comment;
import org.sparta.tech259.finalproject.model.exception.comments.*;
import org.sparta.tech259.finalproject.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentsRestController {
    private final CommentService commentService;


    public CommentsRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/movie/comments")
    public List<Comment> getAllComments() throws CommentsNotFoundException {
        List<Comment> comments = commentService.getAllComments();
         if (comments.isEmpty()) {
             throw new CommentsNotFoundException();
         }
         return comments;
    }

    @GetMapping("movie/comments/comment-id/{id}")
    public Optional<Comment> getCommentById(@PathVariable String id) throws CommentIdNotFoundException {
        if(id == null || id.isEmpty()) {
            throw new CommentIdNotFoundException(id);
        };
        return commentService.getCommentByCommentId(id);
    }


    @GetMapping("movie/comments/userName")
    public List<Comment> getCommentsByUserName(@RequestParam String username) throws UserNameNotFoundException {
        if(username == null) {
            throw new UserNameNotFoundException(username);
        }
        return commentService.getCommentsByUserName(username);
    }

    @GetMapping("movie/comments/{movieId}")
    public List<Comment> getCommentsByMovieId(@PathVariable String movieId) throws MovieIdNotFoundException {
        if (movieId == null || movieId.isEmpty()) {
           throw new MovieIdNotFoundException(movieId);
        }
        return commentService.getCommentsByMovieId(movieId);
    }
    @PostMapping("/movie/comments")
    public Comment addComment(@RequestBody Optional<Comment>  comment) throws CommentBodyNotFoundException {
        if (comment.isEmpty()) {
             throw new CommentBodyNotFoundException();
        } else {
            commentService.createComment(comment.get());
            return comment.get();
        }

    }
    @PutMapping("/movie/comment/{commentId}")
    public Comment updateComment(@PathVariable String commentId, @RequestBody Optional<Comment> comment) throws CommentIsNullException {
        Optional<Comment> findComment = commentService.getCommentByCommentId(commentId);
        if(findComment.isEmpty()){
          throw new CommentIsNullException(commentId);
        } else{
            return commentService.updateComment(commentId,comment.get());
        }
    }

    @DeleteMapping("movie/comment/{commentId}")
    public String deleteComment(@PathVariable String commentId) throws CommentIsNullException {
        Optional<Comment> findComment = commentService.getCommentByCommentId(commentId);
            if(findComment.isEmpty()) {
             throw new CommentIsNullException(commentId);
            }
        commentService.deleteComment(commentId);
        return "Comment with id " + commentId + "was deleted";
        }

}

