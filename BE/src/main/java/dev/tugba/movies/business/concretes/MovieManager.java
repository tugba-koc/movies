package dev.tugba.movies.business.concretes;

import dev.tugba.movies.business.abstracts.MovieService;
import dev.tugba.movies.business.responses.GetAllMoviesResponse;
import dev.tugba.movies.business.responses.GetByIdMovieResponse;
import dev.tugba.movies.core.utilities.exceptions.MovieNotFoundException;
import dev.tugba.movies.core.utilities.exceptions.NullException;
import dev.tugba.movies.core.utilities.exceptions.constants.ErrorCodeConstants;
import dev.tugba.movies.core.utilities.mappers.ModelMapperService;
import dev.tugba.movies.dataAccess.abstracts.MovieRepository;
import dev.tugba.movies.entities.concretes.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieManager implements MovieService {

    private MovieRepository movieRepository;

    @Autowired
    private ModelMapperService modelMapperService;

    public MovieManager(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<GetAllMoviesResponse> getAllMovies() {

        List<Movie> movies = this.movieRepository.findAll();

        List<GetAllMoviesResponse> moviesResponse = movies.stream()
                .map(movie->this.modelMapperService.forResponse()
                        .map(movie,GetAllMoviesResponse.class)).collect(Collectors.toList());

        return moviesResponse;
    }

    @Override
    public GetByIdMovieResponse getById(String imdbId) {
        Movie movie = this.movieRepository.findByImdbId(imdbId).orElseThrow(() -> new MovieNotFoundException(ErrorCodeConstants.MOVIE_NOT_FOUND.getErrorCode()));
        GetByIdMovieResponse response = this.modelMapperService.forResponse().map(movie,GetByIdMovieResponse.class);

        if(response == null){
            ErrorCodeConstants errorCode = ErrorCodeConstants.NULL_EXCEPTION;
            throw new NullException(errorCode.getErrorCode());
        }
        return response;
    }
}
