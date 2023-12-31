package dev.tugba.movies.dataAccess.abstracts;

import dev.tugba.movies.entities.concretes.Review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {
    Optional<Review> findByReviewId(String reviewId);
    Page<Review> findAllByImdbId(String imdbIb, Pageable pageable);
    Optional<Page<Review>> findByImdbIdAndBodyContaining(String imdbId, String query, Pageable pageable);
}
