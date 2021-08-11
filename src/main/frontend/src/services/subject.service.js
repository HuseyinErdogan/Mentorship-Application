import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:6006/api/subjects";

const getSubjects = () => {
  return axios.get(API_URL, { headers: authHeader() });
};


const addSubject = (subject) => {
    return axios
      .post(API_URL+"/add/", subject, { headers: authHeader() })
  
};


const addSubsubjectToSubject = (subjectName, subsubjectName) => {
  return axios.get(API_URL + "/addSubsubject/"+subjectName+"/"+subsubjectName, { headers: authHeader() });
};


const getSubsubjects = (subject) => {
  return subject.subsubjects;
}


export default {
  getSubjects,
  addSubject,
  addSubsubjectToSubject,
  getSubsubjects
};