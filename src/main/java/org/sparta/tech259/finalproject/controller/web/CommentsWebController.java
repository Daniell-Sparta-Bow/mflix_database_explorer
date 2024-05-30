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

    @GetMapping("/web/movie/comments")
    public String getComments(Model model) {
        model.addAttribute("comments", commentService.getAllComments());
        return "temp_comment";
    }
    @GetMapping("/web/movie/comment/{id}")
    public String getComment(@PathVariable String id, Model model) {
        model.addAttribute("comments", commentService.getCommentByCommentId(id));
        return "temp_comment";
    }

    @GetMapping("/web/movie/comment/edit/{id}")
    public String editComment(@PathVariable String id, Model model) {
        Comment commentToUpdate = commentService.getCommentByCommentId(id).orElse(null);
        model.addAttribute("comment", commentToUpdate);
        return "update-comment";
    }

    @PostMapping("/web/movie/comment/update/{id}")
    public String updateComment(@ModelAttribute("comment") Comment comment){
        comment.setDate(LocalDateTime.now());
        commentService.updateComment(comment.getId(),comment);
        return "redirect:/web/movie/comments";
    }
    @GetMapping("/web/movie/comment/add")
    public String addComment(Model model) {
        Comment newComment = new Comment();
        model.addAttribute("comment", newComment);
        return "add-comment";
    }
    @PostMapping("/web/movie/comment/save-comment")
    public String saveComment(@ModelAttribute("comment") Comment comment){
        comment.setDate(LocalDateTime.now());
        commentService.createComment(comment);
        return "redirect:/web/movie/comments";
    }
    @GetMapping("/web/movie/comment/delete/{id}")
    public String deleteComment(@PathVariable String id) {
        Comment commentToDelete = commentService.getCommentByCommentId(id).orElse(null);
        if (commentToDelete != null) {
            commentService.deleteComment(id);
        }
        return "redirect:/web/movie/comments";
    }

}
