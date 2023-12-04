import { Route, Routes } from 'react-router';
import Layout from '@components/Layout';
import Home from '@components/home/Home';
import Trailer from '@components/trailer/Trailer';
import Reviews from '../components/reviews/Reviews';

const RouteManagement = ({
  movies,
  movie,
  reviews,
  setReviews,
  getMovieData,
}) => {
  return (
    <Routes>
      <Route path='/' element={<Layout />}>
        <Route path='/' element={<Home movies={movies} />} />
        <Route path='/trailer/:ytTrailerId' element={<Trailer />} />
        <Route
          path='/reviews/:movieId'
          element={
            <Reviews
              getMovieData={getMovieData}
              reviews={reviews}
              setReviews={setReviews}
              movie={movie}
            />
          }
        />
      </Route>
    </Routes>
  );
};

export default RouteManagement;
