import { useState } from 'react';
import RouteManagement from './route/RouteManagement';
import { getMovieDataController } from './controllers';

function App() {
  const [movie, setMovie] = useState();
  const [reviews, setReviews] = useState();
  const [error, setError] = useState();

  const getMovieData = (imdbId) => {
    getMovieDataController(imdbId)
      .then((response) => {
        setMovie(response.data);
      })
      .catch((err) => setError(err));
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
