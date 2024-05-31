package org.sparta.tech259.finalproject.model.exception.movies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.ResponseEntity;
import jakarta.servlet.http.HttpServletRequest;
@RestControllerAdvice
public class MoviesExceptionAdvice {

        @ExceptionHandler(MoviesNotFoundException.class)
        public ResponseEntity<MoviesResponse> handleMoviesNotFoundException(MoviesNotFoundException e, HttpServletRequest request) {
            MoviesResponse response = new MoviesResponse(HttpStatus.NOT_FOUND.value(),
                    request.getRequestURL().toString(),
                    e.getMessage());

            return ResponseEntity.status( HttpStatus.NOT_FOUND).body(response);

        }

    @ExceptionHandler(RequestBodyNotFoundException.class)
    public ResponseEntity<MoviesResponse> handleRequestBodyNotFoundException(RequestBodyNotFoundException e, HttpServletRequest request) {
        MoviesResponse response = new MoviesResponse(HttpStatus.NOT_FOUND.value(),
                request.getRequestURL().toString(),
                e.getMessage());

        return ResponseEntity.status( HttpStatus.NOT_FOUND).body(response);

    }
    @ExceptionHandler(MovieIdNotFoundException.class)
    public ResponseEntity<MoviesResponse> handleMovieIdNotFound(MovieIdNotFoundException e, HttpServletRequest request) {
        MoviesResponse response = new MoviesResponse(HttpStatus.NOT_FOUND.value(),
                request.getRequestURL().toString(),
                e.getMessage());

        return ResponseEntity.status( HttpStatus.NOT_FOUND).body(response);

    }

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<MoviesResponse> handleMovieNotFound(MovieNotFoundException e, HttpServletRequest request) {
       MoviesResponse  response = new MoviesResponse(HttpStatus.NOT_FOUND.value(),
                request.getRequestURL().toString(),
                e.getMessage());

        return ResponseEntity.status( HttpStatus.NOT_FOUND).body(response);

    }

}
