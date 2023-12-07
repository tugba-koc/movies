import { useEffect, useRef, useState } from 'react';
import { Button, Col, Container, Dropdown, Form, Row } from 'react-bootstrap';
import { useParams } from 'react-router';
import ReviewForm from '../reviewForm/ReviewForm';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTrash } from '@fortawesome/free-solid-svg-icons';
import { useNavigate } from 'react-router-dom';
import {
  addReviewController,
  handleDeleteReviewController,
  handleFilterQueryAndSortController,
} from '../../controllers';

const Reviews = ({ getMovieData, reviews, movie, setReviews }) => {
  const [error, setError] = useState();
  const [query, setQuery] = useState('');
  const [paginationPage, setPaginationPage] = useState(0);
  const [totalReviewsPage, setTotalReviewsPage] = useState();
  const [sortType, setSortType] = useState('');

  const revText = useRef();
  const queryRef = useRef();

  const navigate = useNavigate();

  const params = useParams();
  const movieId = params.movieId;

  useEffect(() => {
    getMovieData(movieId);
  }, []);

  useEffect(() => {
    handleFilterSortAndPagination(sortType, query, paginationPage);
  }, []);

  const handleFilterSortAndPagination = async (sortType, query, page) => {
    try {
      const query = queryRef.current.value;
      let response = await handleFilterQueryAndSortController(
        movieId,
        sortType,
        query,
        page
      );
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
      await handleFilterSortAndPagination(sortType, query, paginationPage);
    } catch (error) {
      setError('please try again');
    }
  };

  const addReview = async (e) => {
    e.preventDefault();

    try {
      const revBody = revText.current;
      await addReviewController(revBody, movieId);
      await handleFilterSortAndPagination(sortType, query, paginationPage);
    } catch (error) {
      setError('please try again');
    } finally {
      const revBody = revText.current;
      revBody.value = '';
    }
  };

  const handleSort = (sortType) => {
    if (sortType === 'DESC') {
      setSortType('DESC');
      handleFilterSortAndPagination('DESC', query, paginationPage);
    } else if (sortType === 'ASC') {
      setSortType('ASC');
      handleFilterSortAndPagination('ASC', query, paginationPage);
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
            <div className='flex justify-end'>
              <Dropdown>
                <Dropdown.Toggle variant='success' id='dropdown-basic'>
                  Sort
                </Dropdown.Toggle>

                <Dropdown.Menu>
                  <Dropdown.Item onClick={() => handleSort('ASC')}>
                    Asc
                  </Dropdown.Item>
                  <Dropdown.Item onClick={() => handleSort('DESC')}>
                    Desc
                  </Dropdown.Item>
                </Dropdown.Menu>
              </Dropdown>
            </div>

            <Row>
              <Form.Control
                className='mt-2 mb-4'
                placeholder='Write a query ...'
                ref={queryRef}
                onChange={() => {
                  handleFilterSortAndPagination(
                    sortType,
                    queryRef.current.value,
                    paginationPage
                  );
                }}
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
                        handleFilterSortAndPagination(
                          sortType,
                          query,
                          number - 1
                        );
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
