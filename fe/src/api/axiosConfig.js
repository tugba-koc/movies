import axios from 'axios';

export default axios.create({
  baseURL: 'https://54f6-2a02-ff0-3304-50d5-21c6-a419-ae60-7ec4.ngrok-free.app',
  headers: { 'ngrok-skip-browser-warning': true },
});
