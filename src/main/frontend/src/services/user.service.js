import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:6006/api/users";

const getUsers = () => {
  return axios.get(API_URL, { headers: authHeader() });
};

const getUserById = (id) => {
  return axios.get(API_URL + "/get/"+id, { headers: authHeader() });
};

const getUserByUsername = (username) => {
  return axios.get(API_URL + "/getByUsername/"+username, { headers: authHeader() });
};

export default {
  getUsers,
  getUserById,
  getUserByUsername,
};