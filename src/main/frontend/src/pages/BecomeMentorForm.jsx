import React, { useState, useEffect } from "react";

import SubjectService from "../services/subject.service";

import AuthService from "../services/auth.service";

import AppealService from "../services/appeal.service";

import {
  Form,
  Container,
  Dropdown,
  Header,
  Grid,
  Message,
} from "semantic-ui-react";

export default function BecomeMentorForm() {
  const [submitSuccess, setSubmitSuccess] = useState(false);
  const [submitError, setSubmitError] = useState(false);
  const [errorDescription, setErrorDescription] = useState("");

  const subjectOptions = [];
  const [subsubjectOptions, setSubsubjectOptions] = useState([]);
  const [selectedSubsubjects, setSelectedSubsubjects] = useState([]);
  const subsubjects = [];

  const currentUser = AuthService.getCurrentUser();
  const [subjects, setSubjects] = useState([]);

  const [textAreaValue, setTextAreaValue] = useState("");

  const handleOnChange = (e, data) => {
    setSelectedSubsubjects([]);
    selectedSubsubjects.length = 0;
    console.log(selectedSubsubjects);

    subsubjectOptions.length = 0;

    subsubjects
      .filter((subsubject) => subsubject.subject == data.value)
      .map((sbj) =>
        subsubjectOptions.push({
          key: sbj.subsubject,
          value: sbj,
          text: sbj.subsubject,
        })
      );

    console.log(subsubjectOptions.length);
  };
  const handleOnChangeSubsubject = (e, data) => {
    selectedSubsubjects.length = 0;
    data.value.map((subsubject) =>
      selectedSubsubjects.push(subsubject.subsubject)
    );
    console.log(selectedSubsubjects);
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const becomeMentorAppeal = {
      user: currentUser,
      description: textAreaValue,
      subsubjects: selectedSubsubjects,
    };
    console.log(becomeMentorAppeal.description);
    AppealService.addBecomeMentorAppeal(becomeMentorAppeal).then((result) => {
      console.log(result.data);

      if (result.data.success) {
        setSubmitSuccess(true);
        setSubmitError(false);
      } else {
        setSubmitSuccess(false);
        setSubmitError(true);
        setErrorDescription(result.data.message);
      }
    });
  };

  const onChangeTextArea = (e, data) => {
    e.preventDefault();
    setTextAreaValue(data.value);
    console.log(textAreaValue);
  };

  useEffect(() => {
    SubjectService.getSubjects().then((result) => {
      setSubjects(result.data.data);
    });
  });

  return (
    <Container className="mt-5">
      <Form onSubmit={handleSubmit}>
        {subjects &&
          subjects.map((subject) => {
            subjectOptions.push({
              key: subject.subjectName,
              value: subject.subjectName,
              text: subject.subjectName,
            });

            subject.subsubjects.map((subsubject) => {
              subsubjects.push({
                subject: subject.subjectName,
                subsubject: subsubject.subsubjectName,
              });
            });
          })}

        <Grid centered celled="internally">
          <Grid.Row columns={2}>
            <Grid.Column textAlign="center">
              <Header size="medium">Subject</Header>
              <Dropdown
                placeholder="Select Subject"
                fluid
                search
                selection
                options={subjectOptions}
                onChange={handleOnChange}
                className="mb-5"
              />
            </Grid.Column>
            <Grid.Column textAlign="center">
              <Header size="medium">Subsubject</Header>
              <Dropdown
                placeholder="Select Subsubject"
                fluid
                search
                selection
                options={subsubjectOptions}
                onChange={handleOnChangeSubsubject}
                className="mb-5"
                fluid
                multiple
                selection
              />
            </Grid.Column>
          </Grid.Row>

          <Grid.Row>
            <Grid.Column textAlign="center" verticalAlign="middle">
              <Header size="medium" className="my-2">
                Please explain your skills and experiences.
              </Header>
              <Form.TextArea
                placeholder="Tell us more"
                onChange={onChangeTextArea}
              />
            </Grid.Column>
          </Grid.Row>
          <Grid.Row>
          <Form.Checkbox label="I agree to the Terms and Conditions" />
          <Form.Button type="submit">Submit</Form.Button>
          </Grid.Row>
          <Grid.Row className='my-4'>
        
          {submitSuccess && !submitError && (
            <Message positive header={"Appeal has been added succesfully."} />
          )}
          {submitError && !submitSuccess && (
            <Message negative header={errorDescription} />
          )}
          </Grid.Row>
        </Grid>
      </Form>   </Container>
  );
}
