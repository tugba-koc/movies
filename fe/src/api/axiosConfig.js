import axios from 'axios';

export default axios.create({
  baseURL: 'https://90f9-2a02-ff0-3304-50d5-d407-b966-824e-bed2.ngrok-free.app',
  headers: { 'ngrok-skip-browser-warning': true },
});
