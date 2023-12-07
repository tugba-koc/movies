import { Form, Button, Alert, Row } from 'react-bootstrap';

const ReviewForm = ({ handleSubmit, revText, error }) => {
  return (
    <Form>
      <Form.Group className='mb-3' controlId='exampleForm.ControlTextarea1'>
        <Form.Label>Example textarea</Form.Label>
        <Form.Control ref={revText} as='textarea' rows={3} />
      </Form.Group>
      {error ? (
        <Alert key={'warning'} variant={'warning'}>
          {error}
        </Alert>
      ) : null}

      <Row className='flex justify-between flex-nowrap'>
        <div className='w-auto'>
          <Button onClick={(e) => handleSubmit(e)} variant='outline-info'>
            Submit
          </Button>
        </div>
      </Row>
    </Form>
  );
};

export default ReviewForm;
