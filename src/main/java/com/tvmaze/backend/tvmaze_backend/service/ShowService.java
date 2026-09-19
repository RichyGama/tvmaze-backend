package com.tvmaze.backend.tvmaze_backend.service;

import com.tvmaze.backend.tvmaze_backend.client.TvMazeClient;
import com.tvmaze.backend.tvmaze_backend.client.dto.TvMazeSearchResponse;
import com.tvmaze.backend.tvmaze_backend.client.dto.TvMazeShow;
import com.tvmaze.backend.tvmaze_backend.dto.ShowSearchResponse;
import com.tvmaze.backend.tvmaze_backend.model.Comment;
import com.tvmaze.backend.tvmaze_backend.model.Show;
import com.tvmaze.backend.tvmaze_backend.repository.CommentRepository;
import com.tvmaze.backend.tvmaze_backend.repository.ShowRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ShowService {

    private final TvMazeClient tvMazeClient;

    private final ShowRepository showRepository;

    private final CommentRepository commentRepository;

    public ShowService(TvMazeClient tvMazeClient, ShowRepository showRepository, CommentRepository commentRepository) {
        this.tvMazeClient = tvMazeClient;
        this.showRepository = showRepository;
        this.commentRepository = commentRepository;
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

        List<Comment> comments =
                commentRepository.findByShowId(response.show().id());

        return new ShowSearchResponse(
                response.show().id(),
                response.show().name(),
                channel,
                response.show().summary(),
                response.show().genres(),
                comments
        );
    }

    public Show getShow(Integer showId) {

        Show show = showRepository.findById(showId)
                .orElseGet(() -> {
                    TvMazeShow tvMazeShow = tvMazeClient.getTvMazeShow(showId);

                    Show newShow = new Show(
                            tvMazeShow.id(),
                            tvMazeShow.name(),
                            tvMazeShow.summary(),
                            tvMazeShow.genres(),
                            getChannel(tvMazeShow),
                            List.of()
                    );

                    return showRepository.save(newShow);
                });

        List<Comment> comments = commentRepository.findByShowId(showId);

        return new Show(
                show.getId(),
                show.getName(),
                show.getSummary(),
                show.getGenres(),
                show.getChannel(),
                comments
        );
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
