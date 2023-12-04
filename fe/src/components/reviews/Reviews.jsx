import { useEffect, useRef, useState } from 'react';
import { Button, Col, Container, Row } from 'react-bootstrap';
import { useParams } from 'react-router';
import ReviewForm from '../reviewForm/ReviewForm';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTrash } from '@fortawesome/free-solid-svg-icons';
import { useNavigate } from 'react-router-dom';
import {
  addReviewController,
  handleDeleteReviewController,
  handleReviewsPaginatedController,
} from '../../controllers';

const Reviews = ({ getMovieData, reviews, movie, setReviews }) => {
  const [error, setError] = useState();
  const revText = useRef();
  const params = useParams();
  const navigate = useNavigate();

  const movieId = params.movieId;
  const [paginationPage, setPaginationPage] = useState(0);
  const [totalReviewsPage, setTotalReviewsPage] = useState();

  useEffect(() => {
    getMovieData(movieId);
  }, []);

  useEffect(() => {
    handleReviewsPaginated(paginationPage);
  }, []);

  const handleReviewsPaginated = (paginationPage) => {
    handleReviewsPaginatedController(movieId, paginationPage)
      .then((response) => {
        setReviews(response?.content);
        setTotalReviewsPage(response?.totalPages);
      })
      .catch((err) => setError(err));
  };

  const handleDeleteReview = async (reviewId, imdbId) => {
    try {
      await handleDeleteReviewController(reviewId, imdbId);
      await getMovieData(movieId);
      await handleReviewsPaginated(paginationPage);
    } catch (error) {
      setError('please try again');
    }
  };

  const addReview = async (e) => {
    e.preventDefault();

    try {
      const revBody = revText.current;
      await addReviewController(revBody, movieId);
      await getMovieData(movieId);
      await handleReviewsPaginated(paginationPage);
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
                        handleReviewsPaginated(number - 1);
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
