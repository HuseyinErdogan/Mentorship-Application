import React, { useState, useEffect } from "react";
import { Table, Grid, Header, Container } from "semantic-ui-react";
import MentorshipService from "../services/mentorship.service";
import AuthService from "../services/auth.service";
import { Router, Switch, Route, Link } from "react-router-dom";

import { Button, Icon, Image, Item, Label, Message, Segment } from "semantic-ui-react";

export default function MentorPage({match}) {
  const { url } = match;
  const [mentorshipsGiven, setMentorshipsGiven] = useState([]);

  const [error, setError] = useState(false);

  const currentUser = AuthService.getCurrentUser();

  useEffect(() => {
    MentorshipService.getMentorshipsByMentorId(currentUser.userId).then(
      (result) => {
        if(result.data.success){
        setMentorshipsGiven(result.data.data);}
        else{
          setError(true);
        }
      }
    );
  }, []);

  return (
    <Container className="mt-5">
      <Grid celled="internally">
        <Grid.Row textAlign="center" columns={3}>
          <Grid.Column width={3}></Grid.Column>
          <Grid.Column width={10}>
            <Header size="huge">Mentorships Given</Header>
          </Grid.Column>
          <Grid.Column width={3}></Grid.Column>
        </Grid.Row>
        {mentorshipsGiven &&
          mentorshipsGiven.map((mentorship) => (
            <Grid.Row columns={3}>
              <Grid.Column width={4}></Grid.Column>
              <Grid.Column width={8} className="d-flex justify-content-center">
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
                      <Item.Header as="a" style={{ textDecoration: "none" }}>
                        {" "}
                        {mentorship.menteeId}
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
              </Grid.Column>

              <Grid.Column width={4}></Grid.Column>
            </Grid.Row>
          ))}{" "}
        <Grid.Row>
          {mentorshipsGiven.length == 0 && (
            <Grid.Column className="my-3" celled textAlign="center">
              <Message negative>
                <Message.Header size="large">
                  There is no mentoring given.
                </Message.Header>
              </Message>
            </Grid.Column>
          )}
          {error && (
            <Grid.Column className="my-3" celled textAlign="center">
              <Message negative>
                <Message.Header size="large">
                  Error in accessing data
                </Message.Header>
              </Message>
            </Grid.Column>
          )}
        </Grid.Row>
        <Grid.Row celled centered>
          <Grid.Column width={10} textAlign="center">
            <Segment>
              <Header icon>
                <Icon name="address book outline" />
              </Header>
              <Segment.Inline>
                <Button
                  as={Link}
                  to={{
                    pathname: `${url}/mentorshipAppeals`,
                  }}
                  color="teal"
                  size="big"
                >
                  CHECK MENTORSHIP APPEALS 
                </Button>
              </Segment.Inline>
            </Segment>
          </Grid.Column>
        </Grid.Row>
      </Grid>
    </Container>
  );
}
