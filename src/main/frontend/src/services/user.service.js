import React from "react";
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

const addUser = (user) => {
  axios.post(API_URL+"/add", user, { headers: authHeader()});
}

const getMentorsBySubsubjectName = (subsubjectName) => {
  return axios.get(API_URL + "/getBySubsubject/"+subsubjectName, { headers: authHeader() });
} 

const getMentorsByDescription = (description) => {
  return axios.get(API_URL + "/getByDescription/"+description, { headers: authHeader() });
} 

const getUserByGoogleResponse = (response) => {
  const email = response.profileObj.email;
  return axios.get(API_URL + "/getByGoogleAuth/"+email);
};


export default {
  getUsers,
  getUserById,
  getUserByUsername,
  addUser,
  getMentorsBySubsubjectName,
  getMentorsByDescription,
  getUserByGoogleResponse
};