package dev.tugba.movies.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReviewRequest {
    @NonNull
    private String imdbId;

    @NonNull
    private String reviewBody;

    @NonNull
    private String reviewId;
}
