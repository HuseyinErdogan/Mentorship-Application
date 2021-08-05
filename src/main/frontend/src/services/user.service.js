import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:6006/api/test/";

const getUserBoard = () => {
  console.log(authHeader())
  return axios.get(API_URL + "user", { headers: authHeader() });
};

const getModeratorBoard = () => {
  return axios.get(API_URL + "mod", { headers: authHeader() });
};

const getAdminBoard = () => {
  return axios.get(API_URL + "admin", { headers: authHeader() });
};

export default {
  getUserBoard,
  getModeratorBoard,
  getAdminBoard,
};