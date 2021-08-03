import axios from "axios"

export default class SubjectService{

    getSubSubjects(){


          return axios({
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            method: "get",
            url: "http://localhost:6006/api/subjects",
            auth: {
                username: 'john',
                password: '123'
            }
          });
    }   
}