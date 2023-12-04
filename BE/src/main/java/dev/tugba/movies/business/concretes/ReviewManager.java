package dev.tugba.movies.business.concretes;

import dev.tugba.movies.business.abstracts.ReviewService;
import dev.tugba.movies.business.requests.CreateReviewRequest;
import dev.tugba.movies.core.utilities.mappers.ModelMapperService;
import dev.tugba.movies.dataAccess.abstracts.ReviewRepository;
import dev.tugba.movies.entities.concretes.Movie;
import dev.tugba.movies.entities.concretes.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewManager implements ReviewService {
    private ReviewRepository reviewRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ModelMapperService modelMapperService;

    public ReviewManager(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void add(CreateReviewRequest createReviewRequest) {
        /*this.brandBusinessRules.checkIfBrandNameAlreadyExists(createBrandRequest.getName());*/
        Review review = this.modelMapperService.forRequest().map(createReviewRequest,Review.class);
        this.reviewRepository.insert(review);

        // send data to movie collection
        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(createReviewRequest.getImdbId()))
                .apply(new Update().push("reviewIds").value(review))
                .first();
    }

    @Override
    public void delete(String reviewId, String imdbId) {
        Review review = this.reviewRepository.findByReviewId(reviewId).orElseThrow();
        this.reviewRepository.delete(review);

        // remove data from movie collection
        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().pull("reviewIds", Query.query(Criteria.where("reviewId").is(review.getReviewId()))))
                .first();
    }
}
