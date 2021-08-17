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

const addSubsubjectToSubject  = (subjectSubsubject) => {
  return axios.post(API_URL + "/addSubsubject/", subjectSubsubject, {
    headers: authHeader()
  });
};

const getSubsubjects = (subject) => {
  return subject.subsubjects;
}

const deleteSubsubject = (subjectSubsubject) =>{
  return axios.delete(API_URL + "/deleteSubsubject/", subjectSubsubject, {
    headers: authHeader()
  });
}

const deleteSubject = (subjectName) =>{
  return axios.delete(API_URL + "/delete/"+subjectName, {
    headers: authHeader()
  });
}


export default {
  getSubjects,
  addSubject,
  addSubsubjectToSubject,
  getSubsubjects,
  deleteSubject,
  deleteSubsubject
};