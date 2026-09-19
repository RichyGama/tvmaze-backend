package com.tvmaze.backend.tvmaze_backend.controller;

import com.tvmaze.backend.tvmaze_backend.dto.CommentRequest;
import com.tvmaze.backend.tvmaze_backend.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<Void> saveComment(
            @RequestBody CommentRequest request) {

        commentService.saveComment(request);

        return ResponseEntity.ok().build();
    }
}
