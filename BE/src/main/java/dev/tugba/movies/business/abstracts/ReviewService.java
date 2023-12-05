package dev.tugba.movies.business.abstracts;

import org.springframework.data.domain.Page;

import dev.tugba.movies.business.requests.CreateReviewRequest;
import dev.tugba.movies.entities.concretes.Review;

public interface ReviewService {
    void add(CreateReviewRequest createReviewRequest);
    void delete(String reviewId, String imdbId);
    Page<Review> findAllReviewWithPageByImdbId(String imdbId, int page);
    Page<Review> findAllReviewBodyWithPageByBody(String imdbId, String query, int page);
}
