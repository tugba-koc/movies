package dev.tugba.movies.webApi.controllers;

import dev.tugba.movies.business.abstracts.ReviewService;
import dev.tugba.movies.business.requests.CreateReviewRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@AllArgsConstructor
public class ReviewsController {
    private ReviewService reviewService;

    // TODO : success mesajı dön HTTP RESPONSE
    @PostMapping()
    @CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    @ResponseStatus(code= HttpStatus.CREATED)
    public void add(@RequestBody() CreateReviewRequest createReviewRequest){
        this.reviewService.add(createReviewRequest);
    }

    @DeleteMapping("/{imdbId}")
    @ResponseStatus(code= HttpStatus.OK)
    @CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    public void delete(@RequestParam("reviewId") String reviewId, @PathVariable String imdbId){
        this.reviewService.delete(reviewId, imdbId);
    }
}
