import { faVideoSlash } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Button, Container, Nav, Navbar } from 'react-bootstrap';

const Header = () => {
  return (
    <Navbar expand='lg' bg='dark' className='bg-body-tertiary'>
      <Container fluid>
        <Navbar.Brand href='/' style={{ color: 'gold' }}>
          <FontAwesomeIcon icon={faVideoSlash} />
          Gold
        </Navbar.Brand>
        <Navbar.Toggle aria-controls='basic-navbar-nav' />
        <Navbar.Collapse id='basic-navbar-nav'>
          <Nav className='me-auto my-2 my-lg-0'>
            <Nav.Link href='#home'>Home</Nav.Link>
            <Nav.Link href='#link'>Watch List</Nav.Link>
          </Nav>
          <Button variant='outline-info' className='me-2'>
            Login
          </Button>
          <Button variant='outline-info'>Register</Button>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
};

export default Header;
