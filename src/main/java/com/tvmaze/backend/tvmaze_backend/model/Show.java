package com.tvmaze.backend.tvmaze_backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "shows")
public class Show {
    @Id
    private Integer id;

    private String name;
    private String summary;
    private List<String> genres;
    private String channel;
    private List<Comment> comments;

    public Show() {
    }

    public Show(Integer id, String name, String summary, List<String> genres, String channel, List<Comment> comments) {
        this.id = id;
        this.name = name;
        this.summary = summary;
        this.genres = genres;
        this.channel = channel;
        this.comments = comments;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSummary() {
        return summary;
    }

    public List<String> getGenres() {
        return genres;
    }

    public String getChannel() {
        return channel;
    }

    public List<Comment> getComments() {
        return comments;
    }
}
