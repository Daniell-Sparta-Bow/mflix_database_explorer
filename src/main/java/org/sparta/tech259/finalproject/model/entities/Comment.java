package org.sparta.tech259.finalproject.model.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection = "comments")
public class Comment {

  @Id
  private String _id;

  private LocalDateTime date;

  private String email;

  private String movie_id;

  private String text;

  private String name;

  public String getId() {
    return _id;
  }

  public Comment() {}

  public Comment(String id, LocalDateTime date, String email, String movie_id, String text, String name) {
    _id = id;
    this.date = date;
    this.email = email;
    this.movie_id = movie_id;
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

  public String getMovie_id() {
    return movie_id;
  }

  public void setMovie_id(String movie_id) {
    this.movie_id = movie_id;
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
           ", movie_id='" + movie_id + '\'' +
           ", text='" + text + '\'' +
           ", name='" + name + '\'' +
           '}';
  }
}
