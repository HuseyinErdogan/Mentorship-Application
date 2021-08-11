import React, { useState, useEffect } from "react";
import {
  Accordion,
  Container,
  Grid,
  Header,
  Button,
  Form,
  Message,
} from "semantic-ui-react";

import SubjectService from "../services/subject.service";



export default function AdminSubjectPage(props) {

    

  const [subjects, setSubjects] = useState([]);

  useEffect(() => {
    SubjectService.getSubjects().then((result) => setSubjects(result.data));
  });

  let rootPanels = [];

  subjects &&
    subjects.map((subject) => {
      let panels = [];

      subject.subsubjects.map((subsubject) => {
        let panel = {
          key: subsubject.subsubjectId,
          title: subsubject.subsubjectName,
          content: "Level 1A Contents",
        };
        panels.push(panel);
      });

      let contentInner = (
        <div>
            
            <Form >
            <Form.Group className='d-flex justify-content-center'>
                
                <Form.Input
                placeholder='Subsubject Name'
                name='subsubjectName'
                
                />

                <Form.Button type='submit'content='Add subsubject' />
            </Form.Group>
            </Form>
           
          <Accordion.Accordion panels={panels} />
        </div>
      );

      let rootPanel = {
        key: subject.subjectId,
        title: subject.subjectName,
        content: { content: contentInner },
      };

      rootPanels.push(rootPanel);
    });

  const [submitSuccess, setSubmitSuccess] = useState(false);
  const [submitError, setSubmitError] = useState(false);

  const [sbjName, setSbjName] = useState("");

  const onChangeSubjectName = (e) => {
    const sbjName = e.target.value;
    setSbjName(sbjName);
  };
  const handleSubmit = (e) => {
    e.preventDefault();
    const subject = {
      subjectName: sbjName,
    };
    if (subject.subjectName != "" && subject.subjectName != null) {
      SubjectService.addSubject(subject).then(() => {
        setSubmitSuccess(true);
        setSubmitError(false);
      });
    }
    else{
        setSubmitError(true);
        setSubmitSuccess(false);
    }
  };

  return (
    <Container className="mt-5">
      <Grid celled="internally" verticalAlign="top" centered>
        <Grid.Row columns={2}>
          <Grid.Column textAlign="center" width={9}>
            <Accordion defaultActiveIndex={0} panels={rootPanels} styled />
          </Grid.Column>
          <Grid.Column textAlign="center" width={6}>
            <Header> Add New Subject</Header>
            <Form onSubmit={handleSubmit}>
              <Form.Input
                label="Subject Name"
                placeholder="Subject Name"
                value={sbjName}
                onChange={onChangeSubjectName}
              />
              <Button type="submit">Submit</Button>
              {submitSuccess && !submitError && (
                <Message
                  positive
                  header={"Subject has been added succesfully."}
                />
              )}
              {submitError && !submitSuccess && (
                <Message
                  negative
                  header={"Invalid subject name !!"}
                />
              )}
            </Form>
          </Grid.Column>
        </Grid.Row>
      </Grid>
    </Container>
  );
}
