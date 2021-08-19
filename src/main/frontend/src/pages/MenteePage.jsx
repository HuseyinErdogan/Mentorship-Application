import React, { useState, useEffect } from "react";
import { Container, Grid, Header } from "semantic-ui-react";
import MentorshipService from "../services/mentorship.service";
import AuthService from "../services/auth.service";
import { Router, Switch, Route, Link } from "react-router-dom";

import {
  Button,
  Icon,
  Image,
  Item,
  Label,
  Message,
  Segment,
} from "semantic-ui-react";

export default function MenteePage({ match }) {
  const { url } = match;

  const [error, setError] = useState(false);

  const [mentorshipsTaken, setMentorshipsTaken] = useState([]);

  const currentUser = AuthService.getCurrentUser();

  useEffect(() => {
    MentorshipService.getMentorshipsByMenteeId(currentUser.userId).then(
      (result) => {
        console.log(result.data);
        if (result.data.success) {
          setMentorshipsTaken(result.data.data);
        } else {
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
            <Header size="huge">Mentorships Taken</Header>
          </Grid.Column>
          <Grid.Column width={3}>
            {currentUser.role.name == "MENTEE" && (
              <Button
                color='yellow'
                as={Link}
                size="large"
                to={{
                  pathname: `${url}/becomeMentor`,
                  user: currentUser,
                }}
                
              >
                Apply For Become Mentor
              </Button>
            )}
          </Grid.Column>
        </Grid.Row>
        {mentorshipsTaken.map((mentorship) => (
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
                    src="https://react.semantic-ui.com/images/avatar/large/matthew.png"
                  />
                  <Item.Content>
                    <Item.Header as="a" style={{ textDecoration: "none" }}>
                      {mentorship.id}
                    </Item.Header>
                    <Item.Meta>
                      <span className="cinema">
                        {mentorship.situation == 0 && "Phases are not defined."}
                      </span>
                      <span className="cinema">
                        {mentorship.situation == 1 && "Phases are in progress."}
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
          {mentorshipsTaken.length == 0 && (
            <Grid.Column className="my-3" celled textAlign="center">
              <Message negative>
                <Message.Header size="large">
                  There is no mentoring taken.
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
                <Icon name="search" />
              </Header>
              <Segment.Inline>
                <Button
                  as={Link}
                  to={{
                    pathname: `${url}/searchMentor`,
                  }}
                  color="teal"
                  size="big"
                >
                  SEARCH MENTOR
                </Button>
              </Segment.Inline>
            </Segment>
          </Grid.Column>
        </Grid.Row>
      </Grid>
    </Container>
  );
}
