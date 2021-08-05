import React, {useState, useEffect} from 'react'
import { Table } from "semantic-ui-react";
import MentorshipService from'../services/mentorship.service';
import AuthService from '../services/auth.service'
export default function MentorPage() {

    const [mentorships, setMentorships] = useState([])
    const [mentorshipsGiven, setMentorshipsGiven] = useState([])
    const currentUser = AuthService.getCurrentUser();
    useEffect(()=>{
        let mentorshipService = new MentorshipService();
        mentorshipService.getMentorships().then(result=>setMentorships(result.data));
        mentorshipService.getMentorshipsByMentorId().then(result=>setMentorshipsGiven(result.data));
    },[])
    

  return (
    <Table celled inverted selectable>
      <Table.Header>
        <Table.Row>
          <Table.HeaderCell>Mentor</Table.HeaderCell>
          <Table.HeaderCell>Mentee</Table.HeaderCell>
          <Table.HeaderCell>Subject</Table.HeaderCell>
        </Table.Row>
      </Table.Header>

      <Table.Body>
        {
           
            mentorships.map(mentorship=>(
                <Table.Row>
                <Table.Cell>{currentUser.username}</Table.Cell>
                <Table.Cell>{mentorship.menteeId}</Table.Cell>
                <Table.Cell textAlign="right">{mentorship.subsubject.subsubjectName}</Table.Cell>
              </Table.Row>
            ))
        }



      </Table.Body>
    </Table>
  );
}
