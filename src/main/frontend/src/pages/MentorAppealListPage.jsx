import React, { useEffect, useState } from "react";

import AppealService from "../services/appeal.service";
import UserService from"../services/user.service";

import { Button, Card, Grid, Image, Container, Label, Header } from "semantic-ui-react";
import AuthService from "../services/auth.service";
import { ROLE_ADMIN, ROLE_MENTOR } from "./Roles";

export default function MentorAppealListPage() {

  const [appeals, setAppeals] = useState([]);


  useEffect(() => {
    AppealService.getAllBecomeMentorAppeals().then((result) => {
      setAppeals(result.data.data);
    });
  });


  const handleApproveButton = (appeal) =>{

      AppealService.makeMenteeMentor(appeal);
      AppealService.deleteByAppealId(appeal.appealId);
  }
  const handleDeclineButton = (appeal) =>{
      AppealService.deleteByAppealId(appeal.appealId)
  }


  return (
    <Container>
      {AuthService.getCurrentUserRole()==ROLE_ADMIN &&(<Grid  celled>
        <Grid.Row>
          
          <Grid.Column textAlign='center'>
            <Header size='large'>MENTOR APPLICATIONS</Header>
          </Grid.Column>
        </Grid.Row>
        <Grid.Row columns={2}>


          {appeals && appeals.map((appeal) => (
            <Grid.Column width={8} className='my-3'>
            <Card className='w-100'>
              <Card.Content className='my-3'>
                <Image
                  floated="right"
                  size="mini"
                  src="https://react.semantic-ui.com/images/avatar/large/molly.png"
                />
                <Card.Header>{appeal.user.firstName+" "+appeal.user.lastName}</Card.Header>
                <Card.Meta>{appeal.user.username}</Card.Meta>
                <Card.Description className='py-2'>
                  {appeal.description}
                </Card.Description>
                <Card.Meta>
                {appeal.subsubjects.map((subsubject)=><Label color='teal'>{subsubject.subsubjectName}</Label>)}
                </Card.Meta>
              </Card.Content>
              <Card.Content extra>
                <div className="ui two buttons">
                  <Button basic color="green" onClick={()=>handleApproveButton(appeal)}>
                    Approve
                  </Button>
                  <Button basic color="red" onClick={()=> handleDeclineButton(appeal)}>
                    Decline
                  </Button>
                </div>
              </Card.Content>
            </Card>
          </Grid.Column>
          )
            
          )}





          
        </Grid.Row>
      </Grid>
)}

      
    </Container>
  );
}
