package com.tvmaze.backend.tvmaze_backend.repository;

import com.tvmaze.backend.tvmaze_backend.model.Show;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShowRepository extends MongoRepository<Show, Integer> {
}
