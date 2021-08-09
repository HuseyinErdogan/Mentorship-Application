import React, { useState, useEffect } from "react";
import MentorshipService from "../services/mentorship.service";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link,
  useParams,
  useLocation,
  withRouter,
} from "react-router-dom";
import {
  Card,
  Icon,
  Image,
  Step,
  Container,
  Grid,
  Header,
  Tab,
  Rating
} from "semantic-ui-react";

function MentorshipDetailPage() {
  const { id } = useParams();

  const [mentorship, setMentorship] = useState(null);
  const [mentor, setMentor] = useState(null);
  const [mentee, setMentee] = useState(null);

  useEffect(() => {
    MentorshipService.getMentorshipById(id).then((result) =>
      setMentorship(result.data)
    );
  }, []);

  useEffect(() => {
    MentorshipService.getMentorFromMentorshipById(id).then((result) =>
      setMentor(result.data)
    );
  }, []);

  useEffect(() => {
    MentorshipService.getMenteeFromMentorshipById(id).then((result) =>
      setMentee(result.data)
    );
  }, []);

  const panes = [];
  return (
    <Container>
      <Grid celled="internally">
        <Grid.Row>
          <Grid.Column textAlign="center" width={5}>
            <Header size="huge">Mentor</Header>
          </Grid.Column>
          <Grid.Column textAlign="center" width={6}></Grid.Column>
          <Grid.Column textAlign="center" width={5}>
            <Header size="huge">Mentee</Header>
          </Grid.Column>
        </Grid.Row>

        <Grid.Row>
          <Grid.Column textAlign="center" width={5}>
            <Container>
              <Card className="center-inner">
                <Image
                  src="https://react.semantic-ui.com/images/avatar/large/matthew.png"
                  wrapped
                  ui={false}
                />
                <Card.Content>
                  <Card.Header>
                    {mentor && mentor.firstName} {mentor && mentor.lastName}
                  </Card.Header>
                  <Card.Meta>
                    <span className="email">{mentor && mentor.email}</span>
                  </Card.Meta>
                  <Card.Description>
                    {mentor && mentor.role.description}
                  </Card.Description>
                </Card.Content>
                <Card.Content extra></Card.Content>
              </Card>
            </Container>
          </Grid.Column>

          <Grid.Column textAlign="center" width={6}>
            <Container className="mt-5 pt-5">
              <Card className="mt-5 w-100">
                <Card.Content>
                  <Card.Header>
                    <Header size="large">
                      {mentorship && mentorship.subsubject.subsubjectName}
                    </Header>
                  </Card.Header>

                  <Card.Description>
                    <Header size="medium" disabled>
                      {mentorship &&
                        mentorship.situation == 0 &&
                        "Phases are not defined."}
                    </Header>
                    <Header size="medium" disabled>
                      {mentorship &&
                        mentorship.situation == 1 &&
                        "Phases are in progress."}
                    </Header>
                    <Header size="medium" disabled>
                      {mentorship &&
                        mentorship.situation == 2 &&
                        "Phases are done."}
                    </Header>
                  </Card.Description>
                </Card.Content>
                <Card.Content extra></Card.Content>
              </Card>
            </Container>
          </Grid.Column>

          <Grid.Column textAlign="center" width={5}>
            <Container>
              <Card className="center-inner">
                <Image
                  src="https://react.semantic-ui.com/images/avatar/large/matthew.png"
                  wrapped
                  ui={false}
                />
                <Card.Content>
                  <Card.Header>
                    {mentee && mentee.firstName} {mentee && mentee.lastName}{" "}
                  </Card.Header>
                  <Card.Meta>
                    <span className="email">{mentee && mentee.email}</span>
                  </Card.Meta>
                  <Card.Description>
                    {mentee && mentee.role.description}
                  </Card.Description>
                </Card.Content>
                <Card.Content extra></Card.Content>
              </Card>
            </Container>
          </Grid.Column>
        </Grid.Row>

        <Grid.Row>
          <div className="my-5">
            <Step.Group className="w-100">
              {mentorship &&
                mentorship.phases.map((phase) => {
                  {panes.push({
                    menuItem: phase.name,
                    render: () => (
                      <Tab.Pane>
                        Mentor comment: {phase.mentorComment} <br/>
                        Mentee comment: {phase.menteeComment} <br/>

                        Mentor Rating: 
                        <Rating maxRating={5} rating={phase.mentorAssesment} icon='star' size='small' />
                        <br />
                        Mentee Rating: 
                        <Rating maxRating={5} rating={phase.menteeAssesment} icon='star' size='small' />
                        <br />
                        <br />

                      </Tab.Pane>
                    ),
                  })
                
                }
                  
                  return(
                  
                  <Step>
                    <Icon
                      name={phase.done ? "check circle" : "hourglass half"}
                    />
                    <Step.Content>
                      <Step.Title>{phase.name}</Step.Title>
                      <Step.Description>
                        Starting Date: 01.01.2001
                      </Step.Description>
                    </Step.Content>
                    
                  </Step>
                )})}
            </Step.Group>
          </div>
        </Grid.Row>
        <Grid.Row>
          <Container className="my-5">
          <Tab menu={{ fluid: true, vertical: true, tabular: 'left' }} panes={panes} />
          </Container>
        </Grid.Row>
      </Grid>
    </Container>
  );
}

export default withRouter(MentorshipDetailPage);
