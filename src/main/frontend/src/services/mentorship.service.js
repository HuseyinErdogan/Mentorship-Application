import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:6006/api/mentorships";



export default class MentorshipService{


    getMentorships = () => {
        return axios.get(API_URL , { headers: authHeader() });
    };
    
    getMentorshipsByMentorId = () => {
        return axios.get(API_URL + "/getByMentorId" , { headers: authHeader() });
    }

    getMentorshipsByMenteeId = () => {
        return axios.get(API_URL + "/getByMenteeId" , { headers: authHeader() });
    }

}
