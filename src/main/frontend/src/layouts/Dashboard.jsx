import React from 'react'
import { Grid } from 'semantic-ui-react'
import MentorshipTakenList from '../pages/MentorshipTakenList';
import MentorshipsGivenList from '../pages/MentorshipsGivenList';



export default function Dashboard() {
    return (
        <Grid columns={2} divided>
            <Grid.Row>
                <Grid.Column>
                    <MentorshipsGivenList/>
                </Grid.Column>
                <Grid.Column>
                    <MentorshipTakenList/>
                </Grid.Column>
            </Grid.Row>
        </Grid>
    )
}
