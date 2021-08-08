import React, {useState, useEffect} from 'react'
import { Table } from "semantic-ui-react";
import MentorshipService from'../services/mentorship.service';
import AuthService from '../services/auth.service'
import UserService from '../services/user.service'
import { Router ,Switch, Route, Link } from "react-router-dom";


import { Button, Icon, Image, Item, Label } from 'semantic-ui-react'

export default function MentorPage() {


    const [mentorshipsGiven, setMentorshipsGiven] = useState([])
    
    const currentUser = AuthService.getCurrentUser();

   

    let menteeCount = 0;

    useEffect(()=>{
        MentorshipService.getMentorshipsByMentorId(currentUser.userId).then(result=>setMentorshipsGiven(result.data));
    },[])



  return (
      <Item.Group link> 
      {
        
        mentorshipsGiven.map(mentorship => (
          <Item as={Link} to={{
            pathname: '/mentorshipdetail/'+mentorship.id}
          } style={{ textDecoration: 'none' }}>
              <Item.Image size='tiny' src='https://react.semantic-ui.com/images/avatar/large/stevie.jpg' />
                  <Item.Content>
                  <Item.Header as='a' style={{ textDecoration: 'none' }} > { mentorship.menteeId}</Item.Header>
                  <Item.Meta>
                    <span className='cinema'>{mentorship.situation==0 && "Phases are not defined."}</span>
                    <span className='cinema'>{mentorship.situation==1 && "Phases are in progress."}</span>
                    <span className='cinema'>{mentorship.situation==2 && "Phases are done."}</span>
                  </Item.Meta>
                  <Item.Extra>
                    <Label icon='globe' content={mentorship.subsubject.subsubjectName} />
                  </Item.Extra>
                </Item.Content>
            
  
            </Item>))
      }



  </Item.Group>
  );
}
