package com.tvmaze.backend.tvmaze_backend.client.dto;

public record TvMazeSearchResponse(Double score,
                                   TvMazeShow show) {
}
