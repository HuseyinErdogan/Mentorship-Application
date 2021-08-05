import React, { useState, useEffect } from "react";
import { Switch, Route, Link } from "react-router-dom";


import AuthService from '../services/auth.service'


import Login from "../components/Login";
import Profile from "../components/Profile";

import MenteePage from "./MenteePage";
import MentorPage from "./MentorPage";
import AdminPage from "./AdminPage";

import EventBus from "../common/EventBus";

const Dashboard = () => {

  const [showAdminPage, setShowAdminPage] = useState(false);
  const [showMentorPage, setShowMentorPage] = useState(false);
  const [showMenteePage, setShowMenteePage] = useState(false);
  const [currentUser, setCurrentUser] = useState(undefined);

  useEffect(() => {
    const user = AuthService.getCurrentUser();

    if (user) {
      setCurrentUser(user);
      setShowMentorPage(user.role.name=="MENTOR");
      setShowMenteePage(user.role.name=="MENTOR");
      setShowMenteePage(user.role.name=="MENTEE");
      setShowAdminPage(user.role.name=="ADMIN");
    }

    EventBus.on("logout", () => {
      logOut();
    });

    return () => {
      EventBus.remove("logout");
    };
  }, []);


  const logOut = () => {
    AuthService.logout();
    setShowMentorPage(false);
    setShowMenteePage(false);
    setShowAdminPage(false);
    setCurrentUser(undefined);
  };

  return (
    <div>
      <nav className="navbar navbar-expand navbar-dark bg-dark">
        <Link to={"/"} className="navbar-brand">
          HE-Mentorship
        </Link>
        <div className="navbar-nav mr-auto">

          {showMentorPage && (
            <li className="nav-item">
              <Link to={"/mentor"} className="nav-link">
                MENTOR BOARD
              </Link>
            </li>
          )}

          {(showMentorPage||showMenteePage) && (
            <li className="nav-item">
              <Link to={"/mentee"} className="nav-link">
                MENTEE BOARD
              </Link>
            </li>
          )}

          {showAdminPage && (
            <li className="nav-item">
              <Link to={"/admin"} className="nav-link">
                ADMIN BOARD
              </Link>
            </li>
          )}

        </div>

        {currentUser ? (
          <div className="navbar-nav ml-auto">
            <li className="nav-item">
              <Link to={"/profile"} className="nav-link">
                {currentUser.username}
              </Link>
            </li>
            <li className="nav-item">
              <a href="/login" className="nav-link" onClick={logOut}>
                LogOut
              </a>
            </li>
          </div>
        ) : (
          <div className="navbar-nav ml-auto">
            <li className="nav-item">
              <Link to={"/login"} className="nav-link">
                Login
              </Link>
            </li>
          </div>
        )}
      </nav>

      <div className="container mt-3">
        <Switch>
          <Route exact path="/login" component={Login} />
          <Route exact path="/profile" component={Profile} />
          <Route path="/mentee" component={MenteePage} />
          <Route path="/mentor" component={MentorPage} />
          <Route path="/admin" component={AdminPage} />
        </Switch>
      </div>

    </div>
  );
}

export default Dashboard;
