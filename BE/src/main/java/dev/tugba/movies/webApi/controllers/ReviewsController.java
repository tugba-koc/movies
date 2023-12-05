package dev.tugba.movies.webApi.controllers;

import dev.tugba.movies.business.abstracts.ReviewService;
import dev.tugba.movies.business.requests.CreateReviewRequest;
import dev.tugba.movies.entities.concretes.Review;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@AllArgsConstructor
public class ReviewsController {
    private ReviewService reviewService;

    @PostMapping()
    @CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    @ResponseStatus(code= HttpStatus.CREATED)
    public void add(@RequestBody() CreateReviewRequest createReviewRequest){
        this.reviewService.add(createReviewRequest);
    }

    /* api.get(`/api/reviews/${movieId}?page=${page}`); */
    @GetMapping("/{imdbId}")
    @CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    @ResponseStatus(code= HttpStatus.OK)
    public Page<Review> findAllReviewWithPagination(@PathVariable String imdbId, @RequestParam(value = "page") int page){
        return this.reviewService.findAllReviewWithPageByImdbId(imdbId, page);
    }

    /* api.get(`/api/reviews/${movieId}?query=${query}&page=${page}`); */
    @PostMapping("/{imdbId}")
    @CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    @ResponseStatus(code= HttpStatus.OK)
    public Page<Review> findByReviewBodyWithPagination(@PathVariable String imdbId, @RequestParam(value = "query") String query, @RequestParam(value = "page") int page){
        return this.reviewService.findAllReviewBodyWithPageByBody(imdbId, query, page);
    }

    @DeleteMapping("/{imdbId}")
    @CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    @ResponseStatus(code= HttpStatus.OK)
    public void delete(@RequestParam(value = "reviewId") String reviewId, @PathVariable String imdbId){
        this.reviewService.delete(reviewId, imdbId);
    }
}
