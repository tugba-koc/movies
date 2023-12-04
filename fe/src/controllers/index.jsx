import api from '../api/axiosConfig';
import { v4 as uuidv4 } from 'uuid';

export const getMovieDataController = async (movieId) => {
  try {
    const response = await api.get(`/api/movies/${movieId}`);

    return response;
  } catch (error) {
    console.log('an error occured');
  }
};

export const handleDeleteReviewController = async (reviewId, imdbId) => {
  try {
    await api.delete(`/api/reviews/${imdbId}?reviewId=${reviewId}`);
  } catch (error) {
    console.error(error);
  }
};

export const addReviewController = async (revBody, movieId) => {
  const REVIEW_ID = uuidv4().toString().replaceAll('-', '');
  try {
    await api.post('/api/reviews', {
      reviewBody: revBody.value,
      imdbId: movieId,
      reviewId: REVIEW_ID,
    });
  } catch (error) {
    console.error(error);
  }
};
