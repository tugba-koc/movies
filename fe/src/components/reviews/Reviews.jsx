import React, { useEffect, useRef, useState } from 'react';
import { Col, Container, Row } from 'react-bootstrap';
import { useParams } from 'react-router';
import ReviewForm from '../reviewForm/ReviewForm';
import api from '@api/axiosConfig';

const Reviews = ({ getMovieData, reviews, setReviews, movie }) => {
  const [error, setError] = useState();
  const revText = useRef();
  const params = useParams();
  const movieId = params.movieId;

  useEffect(() => {
    getMovieData(movieId);
  }, []);

  const addReview = async (e) => {
    e.preventDefault();

    try {
      const revBody = revText.current;
      await api.post('/api/reviews', {
        reviewBody: revBody.value,
        imdbId: movieId,
      });
      const updatedReviews = [...reviews, { body: revBody.value }];

      setReviews(updatedReviews);
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
                <Row>
                  <Col>{review?.body}</Col>
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
