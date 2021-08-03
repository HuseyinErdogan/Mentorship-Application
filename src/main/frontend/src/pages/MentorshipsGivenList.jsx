import React, {useState, useEffect} from 'react'
import { Table } from 'semantic-ui-react'
import MentorshipService from '../services/MentorshipService'

export default function MentorshipsGivenList() {
  
    
    const [mentorships, setMentorships] = useState([])

    useEffect(()=>{
        let mentorshipService = new MentorshipService();
     //   mentorshipService.getMentorships().then(result=>setMentorships(result.data))
    },[])

    return (
  <Table celled>
    <Table.Header>
      <Table.Row>
        <Table.HeaderCell>Mentor</Table.HeaderCell>
        <Table.HeaderCell>Mentee</Table.HeaderCell>
        <Table.HeaderCell>Subject</Table.HeaderCell>
      </Table.Row>
    </Table.Header>

    <Table.Body>
        {
            mentorships.map(mentorship =>(
                <Table.Row>
                    <Table.Cell>{mentorship.mentorId}</Table.Cell>
                    <Table.Cell>{mentorship.menteeId}</Table.Cell>
                    <Table.Cell>{mentorship.subsubject.subsubjectName}</Table.Cell>
                </Table.Row>
            ))
        }

    </Table.Body>
  </Table>
    )
}
