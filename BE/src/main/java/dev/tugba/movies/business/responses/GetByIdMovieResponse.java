package dev.tugba.movies.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdMovieResponse {
    @Id
    private ObjectId id;
    private String title;
    private String imdbId;
    private String poster;
}