package org.sparta.tech259.finalproject.controller.web;

import org.sparta.tech259.finalproject.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.sparta.tech259.finalproject.model.entities.Comment;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
public class CommentsWebController {

    CommentService commentService;

    public CommentsWebController(CommentService commentService) {
        this.commentService = commentService;
    }
  
    @GetMapping("/movie/comment/edit/{id}")
    public String editComment(@PathVariable String id, Model model) {
        Comment commentToUpdate = commentService.getCommentByCommentId(id).orElse(null);
        model.addAttribute("comment", commentToUpdate);
        return "movie-comment-update";
    }

    @PostMapping("/movie/comment/update/{id}")
    public String updateComment(@ModelAttribute("comment") Comment comment){
        comment.setDate(LocalDateTime.now());
        commentService.updateComment(comment.getId(),comment);
        return "redirect:/movie/comments";
    }
    @GetMapping("/movie/comment/add")
    public String addComment(Model model) {
        Comment newComment = new Comment();
        model.addAttribute("comment", newComment);
        return "movie-comment-add";
    }
    @PostMapping("/movie/comment/save-comment")
    public String saveComment(@ModelAttribute("comment") Comment comment){
        comment.setDate(LocalDateTime.now());
        commentService.createComment(comment);
        return "redirect:/movie/comments";
    }
    @GetMapping("/movie/comment/delete/{id}")
    public String deleteComment(@PathVariable String id) {
        Comment commentToDelete = commentService.getCommentByCommentId(id).orElse(null);
        if (commentToDelete != null) {
            commentService.deleteComment(id);
        }
        return "redirect:/movie/comments";
    }

}
