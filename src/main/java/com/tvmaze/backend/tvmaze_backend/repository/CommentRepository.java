package com.tvmaze.backend.tvmaze_backend.repository;

import com.tvmaze.backend.tvmaze_backend.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findByShowId(Integer showId);
}
