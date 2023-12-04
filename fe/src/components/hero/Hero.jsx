import Carousel from 'react-material-ui-carousel';
import { Paper } from '@mui/material';
import './Hero.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCirclePlay } from '@fortawesome/free-solid-svg-icons';
import { Link, useNavigate } from 'react-router-dom';
import { Button } from 'react-bootstrap';
import { useEffect, useState } from 'react';
import api from '@api/axiosConfig';

const Hero = () => {
  const [movies, setMovies] = useState();
  const getMovies = async () => {
    try {
      const response = await api.get('/api/movies');
      setMovies(response.data);
    } catch (error) {
      console.log('hata oluştu');
    }
  };

  useEffect(() => {
    getMovies();
  }, []);
  const navigate = useNavigate();

  const review = (imdbId) => {
    navigate(`/reviews/${imdbId}`);
  };
  return (
    <div className='movie-carousel-container'>
      <Carousel>
        {movies?.map((movie, index) => {
          return (
            <Paper key={index}>
              <div className='movie-card-container'>
                <div
                  className='movie-card'
                  style={{ '--img': `url(${movie.backdrops[0]})` }}
                >
                  <div className='movie-detail'>
                    <div className='movie-poster'>
                      <img src={movie.poster} alt='' />
                    </div>
                    <div className='movie-title'>
                      <h4>{movie.title}</h4>
                    </div>
                    <div className='movie-buttons-container'>
                      <Link
                        to={`/trailer/${movie.trailerLink.substring(
                          movie.trailerLink.length - 11
                        )}`}
                      >
                        <div className='play-button-icon-container'>
                          <FontAwesomeIcon
                            className='play-button-icon'
                            icon={faCirclePlay}
                          />
                        </div>
                      </Link>
                      <div className='movie-review-button-container'>
                        <Button
                          variant='info'
                          onClick={() => review(movie.imdbId)}
                        >
                          Reviews
                        </Button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </Paper>
          );
        })}
      </Carousel>
    </div>
  );
};

export default Hero;
