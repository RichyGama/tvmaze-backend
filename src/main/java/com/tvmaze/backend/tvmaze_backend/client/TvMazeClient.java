package com.tvmaze.backend.tvmaze_backend.client;

import com.tvmaze.backend.tvmaze_backend.client.dto.TvMazeSearchResponse;
import com.tvmaze.backend.tvmaze_backend.client.dto.TvMazeShow;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class TvMazeClient {

    private RestClient restClient;

    public TvMazeClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public TvMazeSearchResponse[] searchTvMaze(String query) {
        return restClient.get().uri(uriBuilder -> uriBuilder
                        .path("/search/shows")
                        .queryParam("q", query)
                        .build())
                .retrieve()
                .body(TvMazeSearchResponse[].class);
    }

    public TvMazeShow getTvMazeShow(Integer showId) {
        return restClient.get()
                .uri("/shows/{showId}", showId)
                .retrieve()
                .body(TvMazeShow.class);
    }

}
