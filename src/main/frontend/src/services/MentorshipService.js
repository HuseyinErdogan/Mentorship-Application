import axios from "axios"

export default class MentorshipService{

    getMentorships(){


          return axios({
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            method: "get",
            url: "http://localhost:6006/api/mentorships",
            auth: {
                username: 'john',
                password: '123'
            }
          });
    }   
}