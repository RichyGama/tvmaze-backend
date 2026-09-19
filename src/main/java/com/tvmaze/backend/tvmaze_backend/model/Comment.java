package com.tvmaze.backend.tvmaze_backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comments")
public class Comment {

    @Id
    private String id;

    private String comment;
    private Integer rating;
    private Integer showId;

    public Comment() {
    }

    public Comment(String comment, Integer rating, Integer showId) {
        this.comment = comment;
        this.rating = rating;
        this.showId = showId;
    }

    public String getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public Integer getRating() {
        return rating;
    }

    public Integer getShowId() {
        return showId;
    }
}
