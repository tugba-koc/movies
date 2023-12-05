import { useEffect, useRef, useState } from 'react';
import { Button, Col, Container, Form, Row } from 'react-bootstrap';
import { useParams } from 'react-router';
import ReviewForm from '../reviewForm/ReviewForm';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTrash } from '@fortawesome/free-solid-svg-icons';
import { useNavigate } from 'react-router-dom';
import {
  addReviewController,
  handleDeleteReviewController,
  handleFilterQueryController,
} from '../../controllers';

const Reviews = ({ getMovieData, reviews, movie, setReviews }) => {
  const [error, setError] = useState();
  const [query, setQuery] = useState('');
  const [paginationPage, setPaginationPage] = useState(0);
  const [totalReviewsPage, setTotalReviewsPage] = useState();

  const revText = useRef();
  const queryRef = useRef();

  const navigate = useNavigate();

  const params = useParams();
  const movieId = params.movieId;

  useEffect(() => {
    getMovieData(movieId);
  }, []);

  useEffect(() => {
    handleFilterAndPagination(query, paginationPage);
  }, []);

  const handleFilterAndPagination = async (query, page) => {
    try {
      const query = queryRef.current.value;
      let response = await handleFilterQueryController(movieId, query, page);
      if (response) {
        setReviews(response?.content);
        setTotalReviewsPage(response?.totalPages);
      }
    } catch (error) {
      console.log('error happened');
    }
  };

  const handleDeleteReview = async (reviewId, imdbId) => {
    try {
      await handleDeleteReviewController(reviewId, imdbId);
      await handleFilterAndPagination(query, paginationPage);
    } catch (error) {
      setError('please try again');
    }
  };

  const addReview = async (e) => {
    e.preventDefault();

    try {
      const revBody = revText.current;
      await addReviewController(revBody, movieId);
      await handleFilterAndPagination(query, paginationPage);
    } catch (error) {
      setError('please try again');
    } finally {
      const revBody = revText.current;
      revBody.value = '';
    }
  };

  return (
    <>
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
            <Row>
              <Form.Control
                className='mt-2 mb-4'
                placeholder='Write a query ...'
                ref={queryRef}
                onChange={() =>
                  handleFilterAndPagination(
                    queryRef.current.value,
                    paginationPage
                  )
                }
              />
            </Row>
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
            <div>
              {totalReviewsPage > 1
                ? Array.from(
                    { length: totalReviewsPage },
                    (_, index) => index + 1
                  ).map((number) => (
                    <Button
                      className='mx-2'
                      key={number}
                      onClick={() => {
                        handleFilterAndPagination(query, number - 1);
                        navigate(`?page=${number}`);
                      }}
                    >
                      {number}
                    </Button>
                  ))
                : null}
            </div>
          </Col>
        </Row>

        <Row>
          <Col>
            <hr />
          </Col>
        </Row>
      </Container>
    </>
  );
};

export default Reviews;
