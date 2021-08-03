import axios from "axios"

export default class UserService{

    getUsers(){


          return axios({
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            method: "get",
            url: "http://localhost:6006/api/users",
            auth: {
                username: 'john',
                password: '123'
            }
          });
    }   
}