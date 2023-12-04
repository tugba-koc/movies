package dev.tugba.movies.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllMoviesResponse {
    private ObjectId id;
    private String imdbId;
    private String title;
    private String poster;
    private List<String> backdrops;
    private String trailerLink;
}
