package org.sparta.tech259.finalproject.model.exception.comments;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.servlet.http.HttpServletRequest;
@RestControllerAdvice
public class CommentExceptionAdvice {

   @ExceptionHandler(CommentIsNullException.class)
   public ResponseEntity<Response> handleCommentIsNullException(CommentIsNullException e, HttpServletRequest request) {
    Response response = new Response(HttpStatus.NOT_FOUND.value(),
            request.getRequestURL().toString(),
                  e.getMessage());

         return ResponseEntity.status( HttpStatus.NOT_FOUND).body(response);

    }

    @ExceptionHandler(CommentBodyNotFoundException.class)
    public ResponseEntity<Response> handleCommentBodyNotFound(CommentBodyNotFoundException e, HttpServletRequest request) {
        Response response = new Response(HttpStatus.NOT_FOUND.value(),
                request.getRequestURL().toString(),
                e.getMessage());

        return ResponseEntity.status( HttpStatus.NOT_FOUND).body(response);

    }
    @ExceptionHandler(CommentIdNotFoundException.class)
    public ResponseEntity<Response> handleCommentIdNotFound(CommentIdNotFoundException e, HttpServletRequest request) {
        Response response = new Response(HttpStatus.NOT_FOUND.value(),
                request.getRequestURL().toString(),
                e.getMessage());

        return ResponseEntity.status( HttpStatus.NOT_FOUND).body(response);

    }
    @ExceptionHandler(MovieIdNotFoundException.class)
    public ResponseEntity<Response> handleMovieIdNotFound(MovieIdNotFoundException e, HttpServletRequest request) {
        Response response = new Response(HttpStatus.NOT_FOUND.value(),
                request.getRequestURL().toString(),
                e.getMessage());

        return ResponseEntity.status( HttpStatus.NOT_FOUND).body(response);

    }
    @ExceptionHandler(UserNameNotFoundException.class)
    public ResponseEntity<Response> handleUserNameNotFound(UserNameNotFoundException e, HttpServletRequest request) {
        Response response = new Response(HttpStatus.NOT_FOUND.value(),
                request.getRequestURL().toString(),
                e.getMessage());

        return ResponseEntity.status( HttpStatus.NOT_FOUND).body(response);

    }
    @ExceptionHandler(CommentsNotFoundException.class)
    public ResponseEntity<Response> handleCommentsNotFound(CommentsNotFoundException e, HttpServletRequest request) {
        Response response = new Response(HttpStatus.NOT_FOUND.value(),
                request.getRequestURL().toString(),
                e.getMessage());

        return ResponseEntity.status( HttpStatus.NOT_FOUND).body(response);

    }
}
