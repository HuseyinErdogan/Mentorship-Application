import React, { useState, useEffect}  from "react";

import SubjectService from "../services/subject.service";

import AppealService from "../services/appeal.service";

import { Form, Container, Dropdown, Header, Grid } from "semantic-ui-react";

export default function BecomeMentorForm(props){


  const [submitSuccess, setSubmitSuccess] = useState(false);
  const [submitError, setSubmitError] = useState(false);


  const currentUser = props.location.user;
  const [subjects, setSubjects] = useState([]);

  let selectedSubsubjects = [];
  
  let textAreaValue = "";

  const handleOnChange = (e, data) => {
    selectedSubsubjects.length=0;
    subsubjectOptions.length=0;
    subsubjects.filter(subsubject=>subsubject.subject==data.value)
      .map(sbj => subsubjectOptions.push({ key: sbj.subsubject, value: sbj, text: sbj.subsubject}));


    console.log(subsubjectOptions.length)
 }
 const handleOnChangeSubsubject = (e, data) => {
   selectedSubsubjects.length = 0;
    data.value.map(subsubject => selectedSubsubjects.push(subsubject.subsubject));
    console.log(selectedSubsubjects);
 }

 const handleSubmit=(e) =>{
    e.preventDefault();

    const becomeMentorAppeal = {
      user:currentUser,
      description: textAreaValue,
      subsubjects: selectedSubsubjects
    }

    AppealService.addBecomeMentorAppeal(becomeMentorAppeal);



 }

 const onChangeTextArea = ( e, data) => {
   e.preventDefault();
   textAreaValue = data.value;
 }

  const subjectOptions = []
  const subsubjectOptions = []
  const subsubjects = []

  useEffect(() => {
    SubjectService.getSubjects().then((result) => {
      setSubjects(result.data);
    });
  });

  return (
    <Container className="mt-5">
      <Form onSubmit={handleSubmit}>
        
        {subjects &&
          subjects.map((subject) => {

            subjectOptions.push({ key: subject.subjectName, value: subject.subjectName, text: subject.subjectName});
            
            subject.subsubjects.map(subsubject=>{
              subsubjects.push({subject:subject.subjectName, subsubject:subsubject.subsubjectName});
            })
          })}

        <Grid centered celled='internally' >
          <Grid.Row columns={2}>
            <Grid.Column textAlign='center'>
        <Header size='medium'>Subject</Header>
          <Dropdown
              placeholder="Select Subject"
              fluid
              search
              selection
              options={subjectOptions}
              onChange={handleOnChange}
              className='mb-5'
            />
          </Grid.Column>
          <Grid.Column textAlign='center'>
          <Header size='medium'>Subsubject</Header>
            <Dropdown
              placeholder="Select Subsubject"
              fluid
              search
              selection
              options={subsubjectOptions}
              onChange={handleOnChangeSubsubject}
              className='mb-5'
              fluid multiple selection
            />
          </Grid.Column>
          </Grid.Row>
        
          <Grid.Row>
            <Grid.Column textAlign='center' verticalAlign='middle'>
        <Header size='medium' className='my-2'>Please explain your skills and experiences.</Header>
        <Form.TextArea placeholder="Tell us more" onChange={onChangeTextArea} />
        </Grid.Column>
        </Grid.Row>
        <Form.Checkbox  label="I agree to the Terms and Conditions" />
        <Form.Button type="submit">Submit</Form.Button>


        </Grid>
      </Form>
    </Container>
  );
}
