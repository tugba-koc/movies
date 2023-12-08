package dev.tugba.movies.webApi.controllers;

import dev.tugba.movies.business.abstracts.ReviewService;
import dev.tugba.movies.business.requests.CreateReviewRequest;
import dev.tugba.movies.entities.concretes.Review;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@AllArgsConstructor
public class ReviewsController {
    private ReviewService reviewService;

    @PostMapping()
    @CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    public ResponseEntity<CreateReviewRequest> add(@RequestBody CreateReviewRequest createReviewRequest) {
        CreateReviewRequest createdReview = this.reviewService.add(createReviewRequest);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    @DeleteMapping("/{imdbId}")
    @CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    public ResponseEntity<String> delete(@RequestParam(value = "reviewId") String reviewId, @PathVariable String imdbId){
        this.reviewService.delete(reviewId, imdbId);
        return new ResponseEntity<>("Review deleted", HttpStatus.OK);
    }

    /* api.get(`/api/reviews/${movieId}?sst=${BODY_BY_DESC}&query=${query}&page=${page}`); */
    @GetMapping("/{imdbId}")
    @CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    @ResponseStatus(code= HttpStatus.OK)
    public ResponseEntity<Page<Review>> sortAllReviewBodyByDescWithPagination(@PathVariable String imdbId, @RequestParam(value = "sst") String sortType, @RequestParam(value = "query") String query, @RequestParam(value = "page") int page){
        return ResponseEntity.ok(this.reviewService.sortAllReviewBodyByDescWithPagination(imdbId, sortType, query, page));
    }
}
