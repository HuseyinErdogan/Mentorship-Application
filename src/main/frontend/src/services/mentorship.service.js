import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:6006/api/mentorships";




const getMentorships = () => {
    return axios.get(API_URL , { headers: authHeader() });
};

const getMentorshipsByMentorId = (mentorId) => {
    return axios.get(API_URL + "/getByMentorId/" +mentorId, { headers: authHeader() });
}

const getMentorshipsByMenteeId = (menteeId) => {
    return axios.get(API_URL + "/getByMenteeId/" +menteeId , { headers: authHeader() });
}

const getMentorshipById = (id) => {
    return axios.get(API_URL + "/get/" +id , { headers: authHeader() });
}

const getMentorFromMentorshipById = (id) => {
    return axios.get(API_URL + "/getMentor/" +id , { headers: authHeader() });
}

const getMenteeFromMentorshipById = (id) => {
    return axios.get(API_URL + "/getMentee/" +id , { headers: authHeader() });
}



export default {
    getMentorships,
    getMentorshipsByMentorId,
    getMentorshipsByMenteeId,
    getMentorshipById,
    getMentorFromMentorshipById,
    getMenteeFromMentorshipById,
}