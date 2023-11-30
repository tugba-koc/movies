package dev.tugba.movies.business.abstracts;

import dev.tugba.movies.business.requests.CreateReviewRequest;

public interface ReviewService {
    void add(CreateReviewRequest createReviewRequest);
}
