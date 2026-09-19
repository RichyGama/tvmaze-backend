package com.tvmaze.backend.tvmaze_backend.service;

import com.tvmaze.backend.tvmaze_backend.dto.CommentRequest;
import com.tvmaze.backend.tvmaze_backend.model.Comment;
import com.tvmaze.backend.tvmaze_backend.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void saveComment(CommentRequest request) {

        if (request.rating() < 0 || request.rating() > 5) {
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }

        Comment comment = new Comment(
                request.comment(),
                request.rating(),
                request.showId()
        );

        commentRepository.save(comment);
    }
}
