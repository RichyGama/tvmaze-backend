package com.tvmaze.backend.tvmaze_backend.client.dto;

import java.util.List;

public record TvMazeShow(Integer id,
                         String name,
                         List<String> genres,
                         TvMazeChannel network,
                         TvMazeChannel webChannel,
                         String summary) {
}