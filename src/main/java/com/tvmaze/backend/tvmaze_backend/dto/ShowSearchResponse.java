package com.tvmaze.backend.tvmaze_backend.dto;

import com.tvmaze.backend.tvmaze_backend.model.Comment;

import java.util.List;

public record ShowSearchResponse(Integer id,
                                 String name,
                                 String channel,
                                 String summary,
                                 List<String> genres,
                                 List<Comment> comments) {
}
