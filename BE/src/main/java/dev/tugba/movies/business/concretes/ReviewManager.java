package dev.tugba.movies.business.concretes;

import dev.tugba.movies.business.abstracts.ReviewService;
import dev.tugba.movies.business.requests.CreateReviewRequest;
import dev.tugba.movies.core.utilities.exceptions.NullException;
import dev.tugba.movies.core.utilities.exceptions.ReviewNotFoundException;
import dev.tugba.movies.core.utilities.exceptions.constants.ErrorCodeConstants;
import dev.tugba.movies.core.utilities.mappers.ModelMapperService;
import dev.tugba.movies.dataAccess.abstracts.ReviewRepository;
import dev.tugba.movies.entities.concretes.Movie;
import dev.tugba.movies.entities.concretes.Review;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.util.StringUtils;



@Service
public class ReviewManager implements ReviewService {
    private static final int PAGE_SIZE = 5;
    
    private ReviewRepository reviewRepository;

    @Autowired
    private ModelMapperService modelMapperService;

    public ReviewManager(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public CreateReviewRequest add(CreateReviewRequest createReviewRequest) {
        /* this.brandBusinessRules.checkIfBrandNameAlreadyExists(createBrandRequest.getName()); */
        Review review = this.modelMapperService.forRequest().map(createReviewRequest,Review.class);
        Review insertedReview = this.reviewRepository.insert(review);
        if(insertedReview == null) {
            ErrorCodeConstants errorCode = ErrorCodeConstants.NULL_EXCEPTION;
            throw new NullException(errorCode.getErrorCode());
        }
        return createReviewRequest;
    }

    @Override
    public void delete(String reviewId, String imdbId) {
        Review review = this.reviewRepository.findByReviewId(reviewId)
            .orElseThrow(() -> new ReviewNotFoundException(ErrorCodeConstants.REVIEW_NOT_FOUND.getErrorCode()));

        this.reviewRepository.delete(review);
    }

    @Override
    public Page<Review> sortAllReviewBodyByDescWithPagination(String imdbId, String sortType, String query, int page) {
        Pageable pageable;
    
        if (!StringUtils.hasLength(sortType) || "ASC".equals(sortType)) {
            pageable = PageRequest.of(page, PAGE_SIZE, Sort.by(Sort.Direction.ASC, "body"));
        } else {
            pageable = PageRequest.of(page, PAGE_SIZE, Sort.by(Sort.Direction.DESC, "body"));
        }
    
        Optional<Page<Review>> reviewPageOptional = this.reviewRepository.findByImdbIdAndBodyContaining(imdbId, query, pageable);
    
        return reviewPageOptional.orElseThrow(() -> new ReviewNotFoundException("err1092"));
    }
}
