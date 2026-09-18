package com.tvmaze.backend.tvmaze_backend.service;

import com.tvmaze.backend.tvmaze_backend.client.TvMazeClient;
import com.tvmaze.backend.tvmaze_backend.client.dto.TvMazeSearchResponse;
import com.tvmaze.backend.tvmaze_backend.client.dto.TvMazeShow;
import com.tvmaze.backend.tvmaze_backend.dto.ShowSearchResponse;
import com.tvmaze.backend.tvmaze_backend.model.Show;
import com.tvmaze.backend.tvmaze_backend.repository.ShowRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ShowService {

    private final TvMazeClient tvMazeClient;

    private final ShowRepository showRepository;

    public ShowService(TvMazeClient tvMazeClient, ShowRepository showRepository) {
        this.tvMazeClient = tvMazeClient;
        this.showRepository = showRepository;
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

    public Show getShow(Integer showId) {

        return showRepository.findById(showId)
                .orElseGet(() -> {
                    TvMazeShow tvMazeShow = tvMazeClient.getTvMazeShow(showId);

                    Show show = new Show(
                            tvMazeShow.id(),
                            tvMazeShow.name(),
                            tvMazeShow.summary(),
                            tvMazeShow.genres(),
                            getChannel(tvMazeShow)
                    );

                    return showRepository.save(show);
                });
    }

    private String getChannel(TvMazeShow tvMazeShow) {

        if (tvMazeShow.network() != null) {
            return tvMazeShow.network().name();
        }

        if (tvMazeShow.webChannel() != null) {
            return tvMazeShow.webChannel().name();
        }

        return null;
    }


}
