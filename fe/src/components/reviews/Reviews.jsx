import { useEffect, useRef, useState } from 'react';
import { Col, Container, Row } from 'react-bootstrap';
import { useParams } from 'react-router';
import ReviewForm from '../reviewForm/ReviewForm';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTrash } from '@fortawesome/free-solid-svg-icons';

import {
  addReviewController,
  handleDeleteReviewController,
} from '../../controllers';

const Reviews = ({ getMovieData, reviews, movie }) => {
  const [error, setError] = useState();
  const revText = useRef();
  const params = useParams();
  const movieId = params.movieId;

  useEffect(() => {
    getMovieData(movieId);
  }, []);

  const handleDeleteReview = async (reviewId, imdbId) => {
    handleDeleteReviewController(reviewId, imdbId)
      .then(() => {
        getMovieData(movieId);
      })
      .catch((err) => setError(err));
  };

  const addReview = async (e) => {
    e.preventDefault();

    try {
      const revBody = revText.current;
      await addReviewController(revBody, movieId);
      getMovieData(movieId);
    } catch (error) {
      setError('please try again');
    } finally {
      const revBody = revText.current;
      revBody.value = '';
    }
  };

  return (
    <Container>
      <Row>
        <Col></Col>
      </Row>
      <Row className='mt-2'>
        <Col>
          <img src={movie?.poster} alt='' />
        </Col>
        <Col>
          {
            <>
              <Row>
                <Col>
                  <ReviewForm
                    error={error}
                    handleSubmit={addReview}
                    revText={revText}
                    labelText='Write a review?'
                  />
                </Col>
              </Row>
              <Row>
                <Col>
                  <hr />
                </Col>
              </Row>
            </>
          }
          {reviews?.map((review) => {
            return (
              <>
                <Row className='flex justify-between'>
                  <Col>{review?.body}</Col>
                  <Col
                    onClick={() =>
                      handleDeleteReview(review?.reviewId, movieId)
                    }
                    className='flex justify-end'
                  >
                    <FontAwesomeIcon icon={faTrash} />
                  </Col>
                </Row>
                <Row>
                  <Col>
                    <hr />
                  </Col>
                </Row>
              </>
            );
          })}
        </Col>
      </Row>
      <Row>
        <Col>
          <hr />
        </Col>
      </Row>
    </Container>
  );
};

export default Reviews;
