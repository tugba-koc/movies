package dev.tugba.movies.webApi.controllers;

import dev.tugba.movies.business.abstracts.MovieService;
import dev.tugba.movies.business.responses.GetAllMoviesResponse;
import dev.tugba.movies.business.responses.GetByIdMovieResponse;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
@AllArgsConstructor
public class MoviesController {
    private MovieService movieService;

    @GetMapping()
    public List<GetAllMoviesResponse> getAll() {
        return this.movieService.getAllMovies();
    }

    @GetMapping("/{imdbId}")
    public Optional<GetByIdMovieResponse> getById(@PathVariable String imdbId) {
        return movieService.getById(imdbId);
    }
}
