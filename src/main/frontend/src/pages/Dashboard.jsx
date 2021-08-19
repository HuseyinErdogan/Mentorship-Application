import React, { useState, useEffect } from "react";
import { Switch, Route, Link} from "react-router-dom";


import AuthService from '../services/auth.service'


import Login from "../components/Login";
import Profile from "../components/Profile";

import MenteePage from "./MenteePage";
import MentorPage from "./MentorPage";
import AdminPage from "./AdminPage";
import MentorshipDetailPage from "./MentorshipDetailPage"
import AdminSubjectPage from "./AdminSubjectPage"
import MentorAppealListPage from "./MentorAppealListPage"
import ProcessPlanning from "./ProcessPlanning"

import EventBus from "../common/EventBus";
import BecomeMentorForm from "./BecomeMentorForm";
import SearchMentor from "./SearchMentor";
import MentorshipAppealsList from "./MentorshipAppealsList";


import {
  Button,
  Container,
  Icon,
} from "semantic-ui-react";

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
        <Container>
          
        <Link className="navbar-brand text-info">
          <Button size='big' inverted attached>
          JSI-MENTORSHIP
          </Button>
         
        </Link>
        

          

        <div className="navbar-nav mr-auto">
        
          {showMentorPage && (
            <li className="nav-item">
              
                <Link to={"/mentor"} className="nav-link text-info">
                <Button inverted circular>
                  MENTOR BOARD
                  </Button>
                </Link>
              
            </li>
          )}

          {(showMentorPage||showMenteePage) && (
            <li className="nav-item">
                            
                <Link to={"/mentee"} className="nav-link text-info">
                <Button inverted circular>
                  MENTEE BOARD
                  </Button>
                </Link>
              
            </li>
          )}

          {showAdminPage && (
            <li className="nav-item">
                            
                <Link to={"/admin"} className="nav-link text-info">
                <Button inverted circular>
                  ADMIN BOARD
                  </Button>
                </Link>
              
            </li>
          )}

        </div>

        {currentUser ? (
          <div className="navbar-nav ml-auto">
            <li className="nav-item">
            
                <Link to={"/profile"} className="nav-link text-info">
                <Button inverted>
                  <Icon size='large' name='user outline'/>
                  {currentUser.username}
                  </Button>
                </Link>
            
            </li>
            <li className="nav-item">
            
              <a href="/login" className="nav-link text-info" onClick={logOut}>
                <Button inverted>
                <Icon size='large' name='log out'/>
                LogOut
                </Button>
              </a>
              
            </li>
          </div>
        ) : (
          <div className="navbar-nav">
           
              <Link to={"/login"} className="nav-link text-info">
              <Button inverted>
                Login
                </Button>
              </Link>
              
          </div>
        )}
        </Container>
      </nav>
      
      <div className="container mt-3">
        <Switch>
          <Route exact path="/login" component={Login} />
          <Route exact path="/profile" component={Profile} />
          <Route exact path="/admin" component={AdminPage} />
          <Route exact path="/mentorshipdetail/:id" component={MentorshipDetailPage} />
          <Route exact path="/admin/subjects" component={AdminSubjectPage} />
          <Route exact path="/admin/mentorAppeals" component={MentorAppealListPage} />
          <Route exact path="/mentor" component={MentorPage} />
          <Route exact path="/mentor/mentorshipAppeals" component={MentorshipAppealsList} />
          <Route exact path="/mentee" component={MenteePage} />
          <Route exact path="/mentee/becomeMentor" component={BecomeMentorForm} />
          <Route exact path="/mentee/searchMentor" component={SearchMentor} />
          <Route exact path="/mentorshipdetail/:id/processPlanning" component={ProcessPlanning} />
        </Switch>
      </div>

    </div>
  );
}

export default Dashboard;
