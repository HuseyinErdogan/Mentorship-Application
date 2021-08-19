import React from "react";
import AuthService from "../services/auth.service";
import { Label, Table, Divider, Header, Image, Segment } from 'semantic-ui-react'
const Profile = () => {
  const currentUser = AuthService.getCurrentUser();


  return (
    <div className="container p-4 ">
      <Segment color="teal" textAlign="center">
                <Header textAlign="center" size="large">
                  {currentUser.firstName + " " + currentUser.lastName}
                </Header>
                <Label color="teal" className="mb-2" size="large">
                  {currentUser.username}
                </Label>
                <Image
                  src="https://react.semantic-ui.com/images/avatar/large/matthew.png"
                  size="small"
                  centered
                />
                <Divider horizontal>
                  <Header size="large">User Informations</Header>
                </Divider>

                <Table definition>
                  <Table.Body>
                    <Table.Row>
                      <Table.Cell width={4}>E-mail</Table.Cell>
                      <Table.Cell>{currentUser.email}</Table.Cell>
                    </Table.Row>
                    <Table.Row>
                      <Table.Cell>Phone number</Table.Cell>
                      <Table.Cell>{currentUser.phoneNumber}</Table.Cell>
                    </Table.Row>
                  </Table.Body>
                </Table>
                
                

              </Segment>

    </div>
  );
};

export default Profile;