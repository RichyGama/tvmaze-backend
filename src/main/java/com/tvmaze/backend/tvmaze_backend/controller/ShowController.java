package com.tvmaze.backend.tvmaze_backend.controller;


import com.tvmaze.backend.tvmaze_backend.client.dto.TvMazeShow;
import com.tvmaze.backend.tvmaze_backend.dto.ShowSearchResponse;
import com.tvmaze.backend.tvmaze_backend.service.ShowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowController {

    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @GetMapping("/search")
    public List<ShowSearchResponse> searchShows(
            @RequestParam String query) {
        return showService.searchShows(query);
    }

    @GetMapping("/{showId}")
    public TvMazeShow getShow(@PathVariable Integer showId) {
        return showService.getShow(showId);
    }

}
