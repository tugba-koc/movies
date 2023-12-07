import axios from 'axios';

export default axios.create({
  baseURL: ' https://84d8-2a02-ff0-3304-50d5-7951-5c4d-e72b-19b.ngrok-free.app',
  headers: { 'ngrok-skip-browser-warning': true },
});
