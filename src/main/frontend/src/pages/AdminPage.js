import React from "react";
import { Link } from "react-router-dom";
import {
  Button,
  Divider,
  Grid,
  Header,
  Icon,
  Search,
  Segment,
  Container,
} from "semantic-ui-react";

export default function AdminPage() {
  return (
    <Container className='my-5' placeholder>
      <Segment size='large'>
        <Grid columns={2} stackable textAlign="center">
          <Divider vertical>Or</Divider>

          <Grid.Row verticalAlign="middle">
            <Grid.Column as={Link}>
                <br/>
              <Header icon>
                <Icon name="address book outline" />
                See Mentor Applications
              </Header>
              <br/><br/>

            </Grid.Column>

            <Grid.Column as={Link}>
                <br/>
              <Header icon>
                <Icon name="world" />
                Add New Subject
              </Header>
              <br/><br/>

            </Grid.Column>
          </Grid.Row>
        </Grid>
      </Segment>
    </Container>
  );
}
