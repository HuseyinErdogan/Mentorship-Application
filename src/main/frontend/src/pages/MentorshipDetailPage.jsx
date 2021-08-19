import React, { useState, useEffect } from "react";
import MentorshipService from "../services/mentorship.service";
import {
  Link,
  useParams,
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
  Rating,
  Button,
  Label,
  Message,
  Form,
  TextArea,
} from "semantic-ui-react";
import AuthService from "../services/auth.service";

function MentorshipDetailPage({ match }) {
  const currentUser = AuthService.getCurrentUser();
  const ROLE_MENTOR = "MENTOR";
  const ROLE_MENTEE = "MENTEE";
  


  

  const { url } = match;

  const { id } = useParams();

  const [currentRole, setCurrentRole] = useState(ROLE_MENTOR);
  const [mentorship, setMentorship] = useState(null);
  const [mentor, setMentor] = useState(null);
  const [mentee, setMentee] = useState(null);

  var currentPhase = 1;

  const [finishPhase, setFinishPhase] = useState(false);

  useEffect(() => {
    MentorshipService.getMentorshipById(id).then((result) =>{
      setMentorship(result.data.data);
      if(result.data.data.menteeId==currentUser.userId){
        setCurrentRole(ROLE_MENTEE);
      }
    }
    );
  }, []);

  useEffect(() => {
    MentorshipService.getMentorFromMentorshipById(id).then((result) =>
      setMentor(result.data.data)
    );
  }, []);

  useEffect(() => {
    MentorshipService.getMenteeFromMentorshipById(id).then((result) =>
      setMentee(result.data.data)
    );
  }, []);

  const handleSubmit = () => {
    MentorshipService.addMentorship(mentorship);
  };

  var activeIndexTemp=0;

  const handleTabChange = (e,data) => {
    activeIndexTemp = data.activeIndex;
    activeIndexTemp =2;
  }

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

        {mentorship && mentorship.phases.length > 0 && (
          <Grid.Row>
            <div className="my-5">
              <Step.Group className="w-100">
                {mentorship &&
                  mentorship.phases.map((phase) => {
                    {
                      if (phase.done) {
                        currentPhase = phase.phaseNumber + 1;
                      }

                      panes.push({
                        menuItem:
                          "Phase " + phase.phaseNumber + ": " + phase.phaseName,
                        render: () => (
                          <Tab.Pane>
                            <Grid divided="vertically" celled="internally">


                              {currentPhase<phase.phaseNumber && (
                                <Grid.Row centered verticalAlign='middle' className='mt-2'>

                                  <Message color="red" className="w-100 h-100" size='massive'>
                                  This phase process has not yet begun.
                                  </Message>
                                
                              </Grid.Row>
                              )}



                              {currentPhase>=phase.phaseNumber && (<Grid.Row columns={2} centered>
                                <Grid.Column width={7} textAlign="center">
                                  <Header size="small">Starting Date</Header>
                                  <Label color="green" horizontal>
                                    {phase.startingDate}
                                  </Label>
                                </Grid.Column>
                                <Grid.Column width={7} textAlign="center">
                                  <Header size="small">Finishing Date</Header>
                                  <Label color="red" horizontal>
                                    {phase.startingDate}
                                  </Label>
                                </Grid.Column>
                              </Grid.Row>)}

                              {(phase.mentorDone || phase.menteeDone) && (
                                <Grid.Row centered>
                                  <Grid.Column
                                    width={8}
                                    textAlign="center"
                                    centered
                                  >
                                    <Message color="green" className="w-100">
                                      The phase is done.
                                    </Message>
                                  </Grid.Column>
                                </Grid.Row>
                              )}

                              {(phase.mentorDone || phase.menteeDone) && (


                                <Grid.Row centered columns={2}>
                                  {/** MENTOR CARD **/}
                                  <Grid.Column width={7} centered>
                                    
                                    <Grid.Row className="d-flex justify-content-center">
                                      {mentor &&
                                        currentRole == ROLE_MENTOR &&
                                        !phase.mentorDone && (
                                          <Form>
                                            <Header size="tiny">
                                              Please comment on the phase.
                                            </Header>

                                            <TextArea
                                              placeholder="Tell us more"
                                              onChange={(e) =>
                                                (phase.mentorComment =
                                                  e.target.value)
                                              }
                                            />

                                            <Header size="tiny">
                                              Rate your experince.
                                            </Header>

                                            <Rating
                                              maxRating={5}
                                              defaultRating={3}
                                              icon="star"
                                              size="large"
                                              onRate={(e, { rating }) => {
                                                phase.mentorAssesment = rating;
                                              }}
                                            />

                                            <Form.Button
                                              color="teal"
                                              className="mt-2"
                                              onClick={() => {
                                                phase.mentorDone = true;
                                                phase.done = true;
                                                handleSubmit(phase);
                                              }}
                                            >
                                              SUBMIT
                                            </Form.Button>
                                          </Form>
                                        )}
                                      {mentor &&
                                        (currentRole == ROLE_MENTEE ||
                                          (currentRole == ROLE_MENTOR &&
                                            phase.mentorDone)) && (
                                          <Card>
                                            <Card.Content
                                              header={
                                                mentor.firstName +
                                                " " +
                                                mentor.lastName
                                              }
                                              meta="Mentor"
                                            />

                                            <Card.Content
                                              description={phase.mentorComment}
                                            />
                                            <Card.Content
                                              extra
                                              centered
                                              className="d-flex justify-content-center"
                                            >
                                              <Rating
                                                maxRating={5}
                                                defaultRating={
                                                  phase.mentorAssesment
                                                }
                                                icon="star"
                                                size="huge"
                                                disabled
                                              />
                                            </Card.Content>
                                          </Card>
                                        )}
                                    </Grid.Row>
                                  </Grid.Column>

                                  {/** MENTEE CARD **/}
                                  <Grid.Column width={7}>
                                    <Grid.Row className="d-flex justify-content-center">
                                      {mentee &&
                                        (currentRole == ROLE_MENTEE &&
                                        phase.menteeDone==false) && (

                                          
                                          <Form>
                                           
                                            <Header size="tiny">
                                              Please comment on the phase.
                                            </Header>

                                            <TextArea
                                              placeholder="Tell us more"
                                              onChange={(e) =>
                                                (phase.menteeComment =
                                                  e.target.value)
                                              }
                                            />

                                            <Header size="tiny">
                                              Rate your experince.
                                            </Header>

                                            <Rating
                                              maxRating={5}
                                              defaultRating={3}
                                              icon="star"
                                              size="large"
                                              onRate={(e, { rating }) => {
                                                phase.menteeAssesment = rating;
                                              }}
                                            />

                                            <Form.Button
                                              color="teal"
                                              className="mt-2"
                                              onClick={() => {
                                                phase.menteeDone = true;
                                                phase.done = true;
                                                handleSubmit(phase);
                                              }}
                                            >
                                              SUBMIT
                                            </Form.Button>
                                          </Form>
                                        )}
                                      {mentee &&
                                        (currentRole == ROLE_MENTOR ||
                                          (currentRole == ROLE_MENTEE &&
                                            phase.menteeDone)) && (
                                          <Card>
                                            <Card.Content
                                              header={
                                                mentee.firstName +
                                                " " +
                                                mentee.lastName
                                              }
                                              meta="Mentee"
                                            />

                                            <Card.Content
                                              description={phase.menteeComment}
                                            />
                                            <Card.Content
                                              extra
                                              centered
                                              className="d-flex justify-content-center"
                                            >
                                              <Rating
                                                maxRating={5}
                                                defaultRating={
                                                  phase.menteeAssesment
                                                }
                                                icon="star"
                                                size="huge"
                                                disabled
                                              />
                                            </Card.Content>
                                          </Card>
                                        )}
                                    </Grid.Row>
                                  </Grid.Column>
                                </Grid.Row>
                              )}

                              {!phase.mentorDone && !phase.menteeDone && currentPhase==phase.phaseNumber && (
                                <Grid.Row centered>
                                  <Grid.Column width={8} textAlign="center">
                                    <Message color="teal">
                                      The phase process is still ongoing.
                                    </Message>
                                  </Grid.Column>
                                </Grid.Row>
                              )}
                              {!phase.mentorDone && !phase.menteeDone && currentPhase==phase.phaseNumber &&  (
                                <Grid.Row centered>
                                  <Grid.Column width={6} textAlign="center">
                                    <Button
                                      onClick={() => setFinishPhase(true)}
                                      color="teal"
                                      className="w-100 h-100"
                                    >
                                      I WANT TO FINISIH PHASE
                                    </Button>
                                  </Grid.Column>
                                </Grid.Row>
                              )}
                              {currentPhase == phase.phaseNumber &&
                                finishPhase && (
                                  <Grid.Row centered>
                                    <Grid.Column width={14} textAlign="center">
                                      <Form>
                                        <Header size="tiny">
                                          Please comment on the phase.
                                        </Header>
                                        {currentRole == ROLE_MENTOR && (
                                          <TextArea
                                            placeholder="Tell us more"
                                            onChange={(e) =>
                                              (phase.mentorComment =
                                                e.target.value)
                                            }
                                          />
                                        )}
                                        {currentRole == ROLE_MENTEE && (
                                          <TextArea
                                            placeholder="Tell us more"
                                            onChange={(e) =>
                                              (phase.menteeComment =
                                                e.target.value)
                                            }
                                          />
                                        )}
                                        <Header size="tiny">
                                          Rate your experince.
                                        </Header>
                                        {currentRole == ROLE_MENTOR && (
                                          <Rating
                                            maxRating={5}
                                            defaultRating={3}
                                            icon="star"
                                            size="large"
                                            onRate={(e, { rating }) => {
                                              phase.mentorAssesment = rating;
                                            }}
                                          />
                                        )}
                                        {currentRole == ROLE_MENTEE && (
                                          <Rating
                                            maxRating={5}
                                            defaultRating={3}
                                            icon="star"
                                            size="large"
                                            onRate={(e, { rating }) => {
                                              phase.menteeAssesment = rating;
                                            }}
                                          />
                                        )}
                                        {currentRole == ROLE_MENTOR && (
                                          <Form.Button
                                            color="teal"
                                            className="mt-2"
                                            onClick={() => {
                                              phase.mentorDone = true;
                                              phase.done = true;
                                              handleSubmit(phase);
                                            }}
                                          >
                                            SUBMIT
                                          </Form.Button>
                                        )}
                                        {currentRole == ROLE_MENTEE && (
                                          <Form.Button
                                            color="teal"
                                            className="mt-2"
                                            onClick={() => {
                                              phase.menteeDone = true;
                                              phase.done = true;
                                              currentPhase= phase.phaseName;
                                              handleSubmit(phase);
                                            }}
                                          >
                                            SUBMIT
                                          </Form.Button>
                                        )}
                                      </Form>
                                    </Grid.Column>
                                  </Grid.Row>
                                )}
                            </Grid>
                          </Tab.Pane>
                        ),
                      });
                    }

                    return (
                      <Step>
                        <Icon
                          name={phase.done ? "check circle" : "hourglass half"}
                        />
                        <Step.Content>
                          <Step.Title>{phase.phaseName}</Step.Title>
                          <Step.Description>
                            Starting Date: {phase.startingDate}
                          </Step.Description>
                        </Step.Content>
                      </Step>
                    );
                  })}
              </Step.Group>
            </div>
          </Grid.Row>
        )}
        {mentorship && mentorship.phases.length > 0 && (
          <Grid.Row>
            <Container className="my-5">
              <Tab
                menu={{ fluid: true, vertical: true, tabular: "left" }}
                panes={panes}
                onTabChange={handleTabChange}
                defaultActiveIndex={currentPhase-1}
              />
            </Container>
          </Grid.Row>
        )}

        {mentorship && mentorship.phases.length == 0 && (
          <Grid.Row centered>
            <Grid.Column width={9}>
              <Button
                as={Link}
                to={{
                  pathname: `${url}/processPlanning`,
                  mnt: mentorship,
                }}
                color="teal"
                className="w-100"
                size="huge"
              >
                PLAN PROCESS
              </Button>
            </Grid.Column>
          </Grid.Row>
        )}
      </Grid>
    </Container>
  );
}

export default withRouter(MentorshipDetailPage);
