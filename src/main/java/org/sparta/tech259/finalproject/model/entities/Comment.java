package org.sparta.tech259.finalproject.model.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.Objects;


@Document(collection = "comments")
public class Comment {

  @Id
  private String _id;

  private LocalDateTime date;

  private String email;

  @Field("movie_id")
  private ObjectId movieId;

  private String text;

  private String name;



  public String getId() {
    return _id;
  }

  public Comment() {}

  public Comment(String id, LocalDateTime date, String email, ObjectId movieId, String text, String name) {
    _id = id;
    this.date = date;
    this.email = email;
    this.movieId = movieId;
    this.text = text;
    this.name = name;
  }

  public void setId(String id) {
    _id = id;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public ObjectId getMovieId() {
    return movieId;
  }

  public void setMovieId(ObjectId movieId) {
    this.movieId = movieId;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Comment{" +
           "Id='" + _id + '\'' +
           ", date=" + date +
           ", email='" + email + '\'' +
           ", movie_id='" + movieId + '\'' +
           ", text='" + text + '\'' +
           ", name='" + name + '\'' +
           '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Comment comment = (Comment) o;
    return Objects.equals(_id, comment._id) && Objects.equals(movieId, comment.movieId) && Objects.equals(name, comment.name);
  }

  @Override
  public int hashCode() {
    int result = Objects.hashCode(_id);
    result = 31 * result + Objects.hashCode(movieId);
    result = 31 * result + Objects.hashCode(name);
    return result;
  }
}
