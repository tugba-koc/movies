package dev.tugba.movies.business.abstracts;

import dev.tugba.movies.business.responses.GetAllMoviesResponse;
import dev.tugba.movies.business.responses.GetByIdMovieResponse;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<GetAllMoviesResponse> getAllMovies();
    Optional<GetByIdMovieResponse> getById(String imdbId);
}
