package com.tvmaze.backend.tvmaze_backend.service;

import com.tvmaze.backend.tvmaze_backend.client.TvMazeClient;
import com.tvmaze.backend.tvmaze_backend.client.dto.TvMazeSearchResponse;
import com.tvmaze.backend.tvmaze_backend.client.dto.TvMazeShow;
import com.tvmaze.backend.tvmaze_backend.dto.ShowSearchResponse;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ShowService {

    private final TvMazeClient tvMazeClient;

    public ShowService(TvMazeClient tvMazeClient) {
        this.tvMazeClient = tvMazeClient;
    }

    public List<ShowSearchResponse> searchShows(String query) {
        return Arrays.stream(tvMazeClient.searchTvMaze(query))
                .map(this::toShowSearchResponse)
                .toList();
    }

    private ShowSearchResponse toShowSearchResponse(TvMazeSearchResponse response) {
        String channel = response.show().network() != null
                ? response.show().network().name()
                : response.show().webChannel() != null
                ? response.show().webChannel().name()
                : null;

        return new ShowSearchResponse(
                response.show().id(),
                response.show().name(),
                channel,
                response.show().summary(),
                response.show().genres()
        );
    }

    public TvMazeShow getShow(Integer showId) {
        return tvMazeClient.getTvMazeShow(showId);
    }


}
