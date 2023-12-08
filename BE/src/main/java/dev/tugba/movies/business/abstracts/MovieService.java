package dev.tugba.movies.business.abstracts;

import dev.tugba.movies.business.responses.GetAllMoviesResponse;
import dev.tugba.movies.business.responses.GetByIdMovieResponse;

import java.util.List;

public interface MovieService {
    List<GetAllMoviesResponse> getAllMovies();
    GetByIdMovieResponse getById(String imdbId);
}
