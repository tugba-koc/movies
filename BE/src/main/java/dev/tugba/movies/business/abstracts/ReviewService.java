package dev.tugba.movies.business.abstracts;

import org.springframework.data.domain.Page;

import dev.tugba.movies.business.requests.CreateReviewRequest;
import dev.tugba.movies.entities.concretes.Review;

public interface ReviewService {
    CreateReviewRequest add(CreateReviewRequest createReviewRequest);
    void delete(String reviewId, String imdbId);
    Page<Review> sortAllReviewBodyByDescWithPagination(String imdbId, String sortType, String query, int page);
}
