import axios from 'axios';

export default axios.create({
  baseURL: 'https://9f48-2a02-ff0-3304-50d5-a444-db1a-63bc-2801.ngrok-free.app',
  headers: { 'ngrok-skip-browser-warning': true },
});
