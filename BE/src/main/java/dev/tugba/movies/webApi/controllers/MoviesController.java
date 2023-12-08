package dev.tugba.movies.webApi.controllers;

import dev.tugba.movies.business.abstracts.MovieService;
import dev.tugba.movies.business.responses.GetAllMoviesResponse;
import dev.tugba.movies.business.responses.GetByIdMovieResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@AllArgsConstructor
public class MoviesController {
    private MovieService movieService;

    @CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    @GetMapping()
    public List<GetAllMoviesResponse> getAll() {
        return this.movieService.getAllMovies();
    }

    @CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    @GetMapping("/{imdbId}")
    public GetByIdMovieResponse getById(@PathVariable String imdbId) {
        return movieService.getById(imdbId);
    }
}
