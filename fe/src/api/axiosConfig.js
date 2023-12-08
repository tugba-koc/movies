import axios from 'axios';

export default axios.create({
  baseURL: 'https://f3bc-2a02-ff0-3304-50d5-1156-2a7c-3a09-2a8.ngrok-free.app',
  headers: { 'ngrok-skip-browser-warning': true },
});
