package dev.tugba.movies.business.concretes;

import dev.tugba.movies.business.abstracts.ReviewService;
import dev.tugba.movies.business.requests.CreateReviewRequest;
import dev.tugba.movies.core.utilities.mappers.ModelMapperService;
import dev.tugba.movies.dataAccess.abstracts.ReviewRepository;
import dev.tugba.movies.entities.concretes.Movie;
import dev.tugba.movies.entities.concretes.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class ReviewManager implements ReviewService {
    private static final int PAGE_SIZE = 5;
    
    private ReviewRepository reviewRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ModelMapperService modelMapperService;

    public ReviewManager(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public CreateReviewRequest add(CreateReviewRequest createReviewRequest) {
        /* this.brandBusinessRules.checkIfBrandNameAlreadyExists(createBrandRequest.getName()); */
        Review review = this.modelMapperService.forRequest().map(createReviewRequest,Review.class);
        this.reviewRepository.insert(review);
        return createReviewRequest;

        // send data to movie collection for reviewIds 
        // this is not an efficient way, because a review collection has already created on mongodb
        // and send data to just review collection is enough.
        // no need to store review data on 2 collections
        /* mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(createReviewRequest.getImdbId()))
                .apply(new Update().push("reviewIds").value(review))
                .first(); */
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

    @Override
    public Page<Review> findAllReviewWithPageByImdbId(String imdbId, int page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        return this.reviewRepository.findAllByImdbId(imdbId, pageable);
    }

    @Override
    public Page<Review> findAllReviewBodyWithPageByBody(String imdbId, String query, int page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        return this.reviewRepository.findByImdbIdAndBodyContaining(imdbId, query, pageable);
    }

    @Override
    public Page<Review> sortAllReviewBodyByDescWithPagination(String imdbId, String sortType, String query, int page) {
        if("".equals(sortType)){
            Pageable pageable = PageRequest.of(page, PAGE_SIZE);
            return this.reviewRepository.findByImdbIdAndBodyContaining(imdbId, query, pageable);
        }
        
        Sort.Direction direction = Sort.Direction.ASC;
        if ("DESC".equals(sortType)) {
            direction = Sort.Direction.DESC;
        }
        Pageable pageable = PageRequest.of(page, PAGE_SIZE, Sort.by(direction, "body"));
        return this.reviewRepository.findByImdbIdAndBodyContaining(imdbId, query, pageable);
    }
}
