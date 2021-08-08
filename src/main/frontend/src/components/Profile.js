import React from "react";
import AuthService from "../services/auth.service";
import { Label } from 'semantic-ui-react'

import UserService from '../services/user.service'

const Profile = () => {
  const currentUser = AuthService.getCurrentUser();
  const currentAccessToken = AuthService.getCurrentAccessToken();
  let roleColor='yellow';

  if(currentUser.role.name=="ADMIN")
    roleColor='red';
  else if(currentUser.role.name=="MENTOR")
    roleColor='blue';

  return (
    <div className="container p-4 ">
      <header className="jumbotron my-4">
        <h2>
          Profile
        </h2>
      </header>
      <p>
        <strong>Token:</strong> {currentAccessToken.substring(0, 20)} ...{" "}
        {currentAccessToken.substr(currentAccessToken.length - 20)}
      </p>
      <p>
        <strong>Id:</strong> {currentUser.id}
      </p>
      <p>
        <strong>Email:</strong> {currentUser.email}
      </p>
      <strong>Role: </strong>
      <div>
      <Label  color={roleColor} image>
      <img src='https://react.semantic-ui.com/images/avatar/small/christian.jpg' />
      {currentUser.username}
      <Label.Detail>{currentUser.role.name}</Label.Detail>
    </Label>
      </div> 

    </div>
  );
};

export default Profile;