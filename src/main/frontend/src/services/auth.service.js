import axios from "axios";

const API_URL = "http://localhost:6006/api/login/";




const login = (username, password) => {
  const userData = {
      username,
      password
  }
  return axios
    .post(API_URL, userData)
    .then((response) => {
      if (response.data.accessToken) {
        localStorage.setItem("user", JSON.stringify(response.data.user));
        localStorage.setItem("accessToken", JSON.stringify(response.data.accessToken));
      }

      return response.data;
    });


};

const logout = () => {
  localStorage.removeItem("user");
  localStorage.removeItem("accessToken")
};

const getCurrentUser = () => {
  return JSON.parse(localStorage.getItem("user"));
};
const getCurrentUserRole = () => {
  const user = JSON.parse(localStorage.getItem("user"));
  return user.role.name;
};
const getCurrentAccessToken = () => {
  return JSON.parse(localStorage.getItem("accessToken"));
};

export default {
  login,
  logout,
  getCurrentUser,
  getCurrentAccessToken,
  getCurrentUserRole
};