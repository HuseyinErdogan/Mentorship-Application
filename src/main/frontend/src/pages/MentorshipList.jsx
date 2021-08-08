import React, {useState, useEffect} from 'react'
import UserService from '../services/user.service'

import { Button, Icon, Image, Item, Label } from 'semantic-ui-react'
import { render } from '@testing-library/react';



class MentorshipList extends React.Component {
    

    render(){
      const {ment} = this.props 
      return (
      <Item>
        asdadasdasdasd
            <Item.Image size='tiny' src='https://react.semantic-ui.com/images/avatar/large/stevie.jpg' />
                <Item.Content>
                <Item.Header as='a' style={{ textDecoration: 'none' }} > </Item.Header>
                <Item.Meta>
                  <span className='cinema'>{this.props.ment.situation==0 && "Phases are not defined."}</span>
                  <span className='cinema'>{this.props.ment.situation==1 && "Phases are in progress."}</span>
                  <span className='cinema'>{this.props.ment.situation==2 && "Phases are done."}</span>
                </Item.Meta>
                <Item.Extra>
                  <Label icon='globe' content={this.props.ment.subsubject.subsubjectName} />
                </Item.Extra>
              </Item.Content>
          

          </Item>)
    }
}

export default MentorshipList;