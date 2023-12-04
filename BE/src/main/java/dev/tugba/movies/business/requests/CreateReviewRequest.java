package dev.tugba.movies.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReviewRequest {
    private String imdbId;
    private String reviewBody;
    private String reviewId;
}
