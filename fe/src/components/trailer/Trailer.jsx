import { useParams } from 'react-router';
import './Trailer.css';
import ReactPlayer from 'react-player';

const Trailer = () => {
  let params = useParams();
  const key = params.ytTrailerId;

  return (
    <div className='react-player-container'>
      {key !== null ? (
        <ReactPlayer
          width='100%'
          height='100%'
          controls={true}
          playing={true}
          url={`https://www.youtube.com/watch?v=${key}`}
        />
      ) : (
        ''
      )}
    </div>
  );
};

export default Trailer;
