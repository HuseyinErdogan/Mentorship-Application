import React, { useState, useEffect } from "react";
import { useHistory } from "react-router";
import {
  Button,
  Grid,
  Container,
  Form,
  TextArea,
  Label,
  Header
} from "semantic-ui-react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

import MentorshipService from "../services/mentorship.service";


const ProcessPlanning = (props) => {
  let history = useHistory();
  const mnt = props.location.mnt;



  const [mentorshipPhases, setMentorshipPhases] = useState({});

  const [phases, setPhases] = useState([
    {
      phaseNumber: 1,
      phaseName: "",
      phaseDetails: "",
      isDone: false,
      mentorAssesment: 0,
      mentorComment: "",
      menteeAssesment: 0,
      menteeComment: "",
      isMentorDone:false,
      isMenteeDone:false
    },
  ]);

  const [count, setCount] = useState(2);

  const [startingDate, setStartingDate] = useState(new Date());

  const addEmptyPhase = () => {
    setPhases([
      ...phases,
      {
        phaseNumber: count,
        phaseName: "",
        phaseDetails: "",
        isDone: false,
        mentorAssesment: 0,
        mentorComment: "",
        menteeAssesment: 0,
        menteeComment: "",
        isMentorDone:false,
        isMenteeDone:false
      },
    ]);
    setCount(count + 1);
  };

  const handleSubmit = () => {
    setMentorshipPhases({ mentorship: mnt, phases: phases });
    MentorshipService.addPhasesToMentorship(mentorshipPhases).then((result) => {
      history.goBack();
    });
    
  };

  return (
    <Grid celled className=" my-5">
      <Grid.Row
        className="py-5"
        verticalAlign="middle"
        textAlign="center"
        centered
      >
        <Header size="large">Plan Phases</Header>
      </Grid.Row>
      <Grid.Row columns={2} textAlign="center">
        <Grid.Column width={2} verticalAlign="middle">
          <Button
            onClick={() => addEmptyPhase()}
            color="teal"
            className="w-100"
          >
            ADD PHASE{}
          </Button>
        </Grid.Column>
        <Grid.Column width={14}>
          <Grid divided="vertically">
            {phases.map((phase) => (
              <Grid.Row columns={4} verticalAlign="middle">
                <Grid.Column width={2}>
                  <Label color="teal" tag className="text-decoration-none">
                    {phase.phaseNumber}
                  </Label>
                </Grid.Column>
                <Grid.Column width={4}>
                  <Form size="medium" key="phaseName">
                    <Label pointing="below" color="teal">
                      Please enter phase name
                    </Label>
                    <Form.Field>
                      <input
                        placeholder=""
                        onChange={(e) => (phase.phaseName = e.target.value)}
                      />
                    </Form.Field>
                  </Form>
                </Grid.Column>

                <Grid.Column width={6}>
                  <Form>
                    <Label pointing="below" color="teal">
                      Please enter phase description
                    </Label>
                    <TextArea
                      onChange={(e) => (phase.phaseDetails = e.target.value)}
                      placeholder="Tell us more"
                      style={{ minHeight: 100 }}
                    />
                  </Form>
                </Grid.Column>

                <Grid.Column width={4}>
                  <Label pointing="below" color="teal">
                    Please enter due date
                  </Label>
                  <DatePicker
                    selected={phase.finishingDate}
                    onChange={(date) => (phase.finishingDate = date)}
                  />
                </Grid.Column>
              </Grid.Row>
            ))}
          </Grid>
        </Grid.Column>
      </Grid.Row>
      <Grid.Row className="py-5" centered>
        <Grid.Column width={9}>
          <Button
            color="teal"
            className="w-100"
            size="huge"
            onClick={handleSubmit}
          >
            SUBMIT PHASES
          </Button>
        </Grid.Column>
      </Grid.Row>
    </Grid>
  );
};
export default ProcessPlanning;
