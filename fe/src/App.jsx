import api from '@api/axiosConfig';
import { useState } from 'react';
import RouteManagement from './route/RouteManagement';

function App() {
  const [movie, setMovie] = useState();
  const [reviews, setReviews] = useState();
  const [error, setError] = useState();

  const getMovieData = async (movieId) => {
    try {
      const response = await api.get(`/api/movies/${movieId}`);
      setMovie(response.data);
      setReviews(response.data.reviewIds);
    } catch (error) {
      setError(error);
    }
  };

  return (
    <>
      <RouteManagement
        getMovieData={getMovieData}
        reviews={reviews}
        setReviews={setReviews}
        movie={movie}
      />
    </>
  );
}

export default App;
