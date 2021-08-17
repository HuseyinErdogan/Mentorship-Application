import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:6006/api/appeals";

const getAllBecomeMentorAppeals = () => {
  return axios.get(API_URL+"/becomeMentor", { headers: authHeader() });
};


const addBecomeMentorAppeal = (becomeMentorAppeal) => {
    return axios
      .post(API_URL+"/becomeMentor/add/", becomeMentorAppeal, { headers: authHeader() })
};


const getBecomeMentorAppealById = (id) => {
  return axios.get(API_URL + "/becomeMentor/get/"+id, { headers: authHeader() });
};

const makeMenteeMentor = (becomeMentorAppeal) => {

  axios.post(API_URL+"/becomeMentor/makeMenteeMentor", becomeMentorAppeal, { headers: authHeader()});
  
}

const deleteByAppealId = (appealId) => {
  return axios.get(API_URL + "/becomeMentor/delete/"+appealId, { headers: authHeader() });
};










const getAllMentorshipAppeals = () => {
  return axios.get(API_URL+"/mentorshipAppeals", { headers: authHeader() });
};

const addMentorshipAppeal = (becomeMentorAppeal) => {
  return axios
    .post(API_URL+"/mentorshipAppeals/add/", becomeMentorAppeal, { headers: authHeader() })
};


const getMentorshipAppealById = (id) => {
return axios.get(API_URL + "/mentorshipAppeals/get/"+id, { headers: authHeader() });
};

const getMentorshipAppealsByMentorId = (mentorId) => {
  return axios.get(API_URL + "/mentorshipAppeals/getByMentor/"+mentorId, { headers: authHeader() });
};

const getMentorshipAppealsByMenteeId = (menteeId) => {
  return axios.get(API_URL + "/mentorshipAppeals/getByMentee/"+menteeId, { headers: authHeader() });
};

const acceptMentorshipAppeal = (appealId) => {
  return axios.get(API_URL + "/mentorshipAppeals/acceptAppeal/"+appealId, { headers: authHeader() });
}

const deleteMentorshipAppealByAppealId = (appealId) => {
  return axios.delete(API_URL + "/mentorshipAppeals/delete/"+appealId, { headers: authHeader() });
};


export default {
    getAllBecomeMentorAppeals,
    addBecomeMentorAppeal,
    getBecomeMentorAppealById,
    makeMenteeMentor,
    deleteByAppealId,
    getAllMentorshipAppeals,
    addMentorshipAppeal,
    getMentorshipAppealById,
    getMentorshipAppealsByMentorId,
    getMentorshipAppealsByMenteeId,
    acceptMentorshipAppeal,
    deleteMentorshipAppealByAppealId
};