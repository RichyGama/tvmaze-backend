package com.tvmaze.backend.tvmaze_backend.dto;

public record CommentRequest(Integer showId,
                             String comment,
                             Integer rating) {
}
