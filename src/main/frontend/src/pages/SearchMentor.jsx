import React, { useState, useEffect } from "react";
import {
  Button,
  Header,
  Icon,
  Segment,
  Input,
  Grid,
  Container,
  Card,
  Image,
  Divider,
  Table,
  Label,
  Item,
  Message
} from "semantic-ui-react";

import AuthService from "../services/auth.service"
import SubjectService from "../services/subject.service";
import UserService from "../services/user.service";
import MentorshipService from "../services/mentorship.service";
import AppealService from "../services/appeal.service";
import { Link } from "react-router-dom";

const SearchMentor = () => {

  const currentUser = AuthService.getCurrentUser();

  const [applySuccess, setApplySuccess] = useState(false);
  const [applyError, setApplyError] = useState(false);
  const [errorDescription, setErrorDescription] = useState("");




  const [subjects, setSubjects] = useState([]);
  const [mentors, setMentors] = useState([]);

  const [selectedMentor, setSelectedMentor] = useState(null);
  const [selectedSubsubject, setSelectedSubsubject] = useState({});
  const [selectedMentorsOldMentorships, setSelectedMentorsOldMentorships] =
    useState([]);

  useEffect(() => {
    SubjectService.getSubjects().then((result) =>
      setSubjects(result.data.data)
    );
  });

  return (
    <Container>
      <Grid columns={2} divided>
        <Grid.Row stretched>
          <Grid.Column>
            <Segment textAlign="center">
              <Header icon>
                <Icon name="search" />
                Search Mentor
              </Header>
              <br />
              <Input></Input>

              <Segment.Inline>
                <Button color="teal">Clear Query</Button>
                <Button>Add Document</Button>
              </Segment.Inline>
            </Segment>
            {subjects &&
              subjects.map((subject) => (
                <Segment.Group>
                  <Segment>
                    <Header textAlign="center">{subject.subjectName}</Header>
                  </Segment>
                  <Segment.Group>
                    <Segment>
                      <Container>
                        <Grid>
                          <Grid.Row centered columns={2} textAlign="center">
                            {subject.subsubjects.map((subsubject) => (
                              <Grid.Column width={8}>
                                <Button
                                  className="w-100 m-2"
                                  color="yellow"
                                  onClick={() => {
                                    UserService.getMentorsBySubsubjectName(
                                      subsubject.subsubjectName
                                    ).then((result) => {
                                      setMentors(result.data.data);
                                      setSelectedSubsubject(subsubject.subsubjectName);
                                    });
                                  }}
                                >
                                  {subsubject.subsubjectName}
                                </Button>
                              </Grid.Column>
                            ))}
                          </Grid.Row>
                        </Grid>
                      </Container>
                    </Segment>
                  </Segment.Group>
                </Segment.Group>
              ))}
          </Grid.Column>
          <Grid.Column>
            <Segment>
              <Container>
                <Grid divided="vertically">
                  <Grid.Row width={2}>
                    {mentors.map((mentor) => (
                      <Grid.Column width={8}>
                        <Card   
                          className="center-inner text-decoration-none"
                          onClick={() => {
                            setSelectedMentor(mentor);
                            MentorshipService.getMentorsOldMentorships(
                              mentor.userId
                            ).then((result) =>
                              {
                              setSelectedMentorsOldMentorships(result.data.data);}
                            );
                          }}
                        >
                          <Image
                            src="https://react.semantic-ui.com/images/avatar/large/matthew.png"
                            wrapped
                            ui={false}
                          />
                          <Card.Content>
                            <Card.Header>
                              {mentor && mentor.firstName}{" "}
                              {mentor && mentor.lastName}
                            </Card.Header>
                            <Card.Meta>
                              <span className="email">
                                {mentor && mentor.email}
                              </span>
                            </Card.Meta>
                            <Card.Description>
                              {mentor && mentor.role.description}
                            </Card.Description>
                          </Card.Content>
                          <Card.Content extra></Card.Content>
                        </Card>
                      </Grid.Column>
                    ))}
                  </Grid.Row>
                </Grid>
              </Container>
            </Segment>
          </Grid.Column>
        </Grid.Row>

        {selectedMentor && (
          <Grid.Row centered>
            <Grid.Column width={12}>
              <Segment color="teal" textAlign="center">
                <Header textAlign="center" size="large">
                  {selectedMentor.firstName + " " + selectedMentor.lastName}
                </Header>
                <Label color="teal" className="mb-2" size="large">
                  {selectedMentor.username}
                </Label>
                <Image
                  src="https://react.semantic-ui.com/images/avatar/large/matthew.png"
                  size="small"
                  centered
                />
                <Divider horizontal>
                  <Header size="large">Description</Header>
                </Divider>
                <p>{selectedMentor.role.description}</p>
                <Divider horizontal>
                  <Header size="large">Mentor Informations</Header>
                </Divider>

                <Table definition>
                  <Table.Body>
                    <Table.Row>
                      <Table.Cell width={4}>E-mail</Table.Cell>
                      <Table.Cell>{selectedMentor.email}</Table.Cell>
                    </Table.Row>
                    <Table.Row>
                      <Table.Cell>Phone number</Table.Cell>
                      <Table.Cell>{selectedMentor.phoneNumber}</Table.Cell>
                    </Table.Row>
                    <Table.Row>
                      <Table.Cell>Subjects of expertise</Table.Cell>
                      <Table.Cell>
                        {selectedMentor.role.subsubjectsOfExpertise.map(
                          (subsubject) => (
                            <Label color="teal" className="mb-2" size="large">
                              {subsubject.subsubjectName}
                            </Label>
                          )
                        )}
                      </Table.Cell>
                    </Table.Row>
                  </Table.Body>
                </Table>
                {selectedMentorsOldMentorships.length != 0 && (
                  <Divider horizontal>
                    <Header size="large">Old Mentorships</Header>
                  </Divider>
                )}
                {selectedMentorsOldMentorships.length!=0 &&
                  selectedMentorsOldMentorships.map((mentorship) => (
                    <div className="d-flex justify-content-center">
                    <Item.Group>
                      <Item
                        as={Link}
                        to={{
                          pathname: "/mentorshipdetail/" + mentorship.id,
                        }}
                        style={{ textDecoration: "none" }}
                      >
                        <Item.Image
                          size="tiny"
                          src="https://react.semantic-ui.com/images/avatar/large/stevie.jpg"
                        />
                        <Item.Content>
                          <Item.Header
                            as="a"
                            style={{ textDecoration: "none" }}
                          >
                            {mentorship.id}
                          </Item.Header>
                          <Item.Meta>
                            <span className="cinema">
                              {mentorship.situation == 0 &&
                                "Phases are not defined."}
                            </span>
                            <span className="cinema">
                              {mentorship.situation == 1 &&
                                "Phases are in progress."}
                            </span>
                            <span className="cinema">
                              {mentorship.situation == 2 && "Phases are done."}
                            </span>
                          </Item.Meta>
                          <Item.Extra>
                            <Label
                              icon="globe"
                              content={mentorship.subsubject.subsubjectName}
                            />
                          </Item.Extra>
                        </Item.Content>
                      </Item>
                    </Item.Group>
                    </div>
                  ))}

<Divider/>  

                  <Button size='huge' className='w-75' color='teal' onClick={()=>{
                        let mentorTemp = selectedMentor;
                        let menteeTemp = currentUser;
                        const mentorshipAppeal = {
                          mentor: mentorTemp,
                          mentee: menteeTemp,
                          subsubject: selectedSubsubject
                        };

                        AppealService.addMentorshipAppeal(mentorshipAppeal).then((result) => {
                          console.log(result.data)
                          if (result.data.success) {
                            setApplySuccess(true);
                            setApplyError(false);
                          } else {
                            setApplySuccess(false);
                            setApplyError(true);
                            setApplyError(result.data.message);
                          }
                        });
                  }}>Apply For Mentorship</Button>
                  
                  <Divider/>
                  {applySuccess && (
                    <Message positive header={"Appeal has been added succesfully."} />
                  )}
                  {applyError && (
                    <Message negative header={applyError} />
                  )}
              </Segment>
            </Grid.Column>
          </Grid.Row>
        )}
      </Grid>
    </Container>
  );
};
export default SearchMentor;
