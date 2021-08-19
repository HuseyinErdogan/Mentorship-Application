import React, { useState, useRef } from "react";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import GoogleLogin from "react-google-login";
import AuthService from "../services/auth.service";
import UserService from "../services/user.service";
import {ROLE_MENTOR, ROLE_MENTEE, ROLE_ADMIN} from "../pages/Roles";

const required = (value) => {
  if (!value) {
    return (
      <div className="alert alert-danger" role="alert">
        This field is required!
      </div>
    );
  }
};

const Login = (props) => {
  const form = useRef();
  const checkBtn = useRef();

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState("");

  const onChangeUsername = (e) => {
    const username = e.target.value;
    setUsername(username);
  };

  const onChangePassword = (e) => {
    const password = e.target.value;
    setPassword(password);
  };

  const handleLogin = (e) => {
    e.preventDefault();

    setMessage("");
    setLoading(true);

    form.current.validateAll();

    if (checkBtn.current.context._errors.length === 0) {
      AuthService.login(username, password).then(
        (response) => {
        
          if(response.user.role.name==ROLE_ADMIN){
              props.history.push("/admin");
              window.location.reload();
          }
          else if(response.user.role.name==ROLE_MENTOR){
              props.history.push("/mentor");
              window.location.reload();
          }
          else if(response.user.role.name==ROLE_MENTEE){
              props.history.push("/mentee");
              window.location.reload();
          }
          

        },
      ).catch(function(error){

        if(error.response.status==401){

          setLoading(false);
          setMessage("Username or password is incorrect");
        }
      })
    } else {
      setLoading(false);
    }
  };

  const responseGoogle = (response) =>{
    UserService.getUserByGoogleResponse(response).then((result)=>{

      const username = result.data.data.username;
      const password = result.data.data.userPassword;
      AuthService.login(username, password).then(
        () => {
          props.history.push("/profile");
          window.location.reload();

        },
      )

    })
  }


  return (
    <div className="container">
      <div className="col-md-12">

        <div className="card card-container">
          
          <img
            src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
            alt="profile-img"
            className="profile-img-card"
          />

          <Form onSubmit={handleLogin} ref={form}>
            <div className="form-group">
              <label htmlFor="username">Username</label>
              <Input
                type="text"
                className="form-control"
                name="username"
                value={username}
                onChange={onChangeUsername}
                validations={[required]}
              />
            </div>

            <div className="form-group">
              <label htmlFor="password">Password</label>
              <Input
                type="password"
                className="form-control"
                name="password"
                value={password}
                onChange={onChangePassword}
                validations={[required]}
              />
            </div>

            <div className="form-group my-2">
              <button className="btn btn-primary btn-block w-100" disabled={loading}>
                {loading && (
                  <span className="spinner-border spinner-border-sm"></span>
                )}
                <span>Login</span>
              </button>
            </div>

            {message && (
              <div className="form-group">
                <div className="alert alert-danger" role="alert">
                  {message}
                </div>
              </div>
            )}
            <CheckButton style={{ display: "none" }} ref={checkBtn}/>
            <GoogleLogin
            className='w-100'
        clientId="234841668420-4p7cjggsqingudd8jguvf8uaddkfrvbj.apps.googleusercontent.com"
        buttonText="LOGIN WITH GOOGLE"
        onSuccess={responseGoogle}
        onFailure={responseGoogle}
        cookiePolicy={'single_host_origin'}
        
        />
          </Form>
        </div>
      </div>
    </div>

    
  );
};

export default Login;
