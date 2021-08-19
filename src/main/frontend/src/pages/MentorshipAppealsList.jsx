import React, { useEffect, useState } from "react";

import AppealService from "../services/appeal.service";
import AuthService from"../services/auth.service";

import { Button, Card, Grid, Image, Container, Header, Message} from "semantic-ui-react";

const MentorshipAppealsList = () => {

    const [approveSuccess, setApproveSuccess] = useState(false);
    const [approveError, setApproveError] = useState(false);
    const [errorDescription, setErrorDescription] = useState("");

    const currentUser = AuthService.getCurrentUser();



  const [appeals, setAppeals] = useState([]);


  useEffect(() => {
    AppealService.getMentorshipAppealsByMentorId(currentUser.userId).then((result) => {
      setAppeals(result.data.data);
    });
  });


  const handleApproveButton = (appeal) =>{

      AppealService.acceptMentorshipAppeal(appeal.appealId).then((result)=>{
          if(result.data.success){
              setApproveSuccess(true);
              setApproveError(false);
              AppealService.deleteMentorshipAppealByAppealId(appeal.appealId);
          }else{
            setApproveSuccess(false);
            setApproveError(true);
            setErrorDescription(result.data.message)
          }
      });

  }
  const handleDeclineButton = (appeal) =>{
      AppealService.deleteMentorshipAppealByAppealId(appeal.appealId);
  }


  return (
    <Container>
      <Grid  celled>
        <Grid.Row>
          
          <Grid.Column textAlign='center'>
            <Header size='large'>MENTORSHIP APPEALS</Header>
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
                  src="https://react.semantic-ui.com/images/avatar/large/matthew.png"
                />
                <Card.Header>{appeal.mentee.firstName+" "+appeal.mentee.lastName}</Card.Header>
                <Card.Meta>{appeal.mentee.username}</Card.Meta>
                <Card.Description className='py-2'>
                  {appeal.description}
                </Card.Description>
                <Card.Meta>
                    {appeal.mentee.email}
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
        
                {appeals.length==0 && (<Grid.Row centered>
                    <Grid.Column width={13} textAlign='center'>
                        <Message negative header={"There is no mentorship appeal."} />
                    </Grid.Column>
                </Grid.Row>)}


            <Grid.Row centered >
            {approveSuccess && !approveError && (
                <Grid.Column width={10} textAlign='center'>

                
            <Message positive header={"Mentorship is successfully added."} />
            </Grid.Column>
          )}
            {approveError && !approveSuccess && (
                <Grid.Column width={10}>
                <Message negative header={errorDescription} />
                </Grid.Column>
            )}
            </Grid.Row>


      </Grid>


      
    </Container>
  );
}
export default MentorshipAppealsList;