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

  const [submitSuccess, setSubmitSuccess] = useState(false);
  const [submitError, setSubmitError] = useState(false);
  const [errorDescription, setErrorDescription] = useState("");

  const [subsubjectSubmitSuccess, setSubsubjectSubmitSuccess] = useState(false);
  const [subsubjectSubmitError, setSubsubjectSubmitError] = useState(false);
  const [errorSubsubjectSubmitDescription, setErrorSubsubjectSubmitDescription] = useState("");

  const [subjects, setSubjects] = useState([]);


  const handleDeleteSubject = (subjectName) =>{
    SubjectService.deleteSubject(subjectName).then((response)=>{
      console.log(response);
    })
  }

  const handleDeleteSubsubject = (subsubjectName) =>{
    SubjectService.deleteSubsubject(subsubjectName).then((response)=>{
      console.log(response);
    })
  }

  useEffect(() => {
    SubjectService.getSubjects().then((result) => setSubjects(result.data.data));
  });

  let rootPanels = [];

  const [subsbjName, setSubsbjName] = useState("");


  subjects &&
    subjects.map((subject) => {
      let panels = [];




      subject.subsubjects.map((subsubject) => {

        let innerInnerContent = (
          <Grid>
            <Grid.Row centered columns={3}>

              <Grid.Column centered textAlign='center' width={10}>
              <Button color='red' className='mb-3' onClick={()=>{
                handleDeleteSubsubject(subsubject.subsubjectName)
              }}>
                Delete Subsubject
              </Button>
              </Grid.Column>

            </Grid.Row>
          </Grid>
          

        ) 


        let panel = {
          key: subsubject.subsubjectId,
          title: subsubject.subsubjectName,
          content: innerInnerContent
        };
        panels.push(panel);
      });

      let contentInner =  (
        <div>
            
            <Form>
            <Form.Group className='d-flex justify-content-center'>
                
                <Form.Input
                placeholder='Subsubject Name'
                name='subsubjectName'
                onChange={(e,data)=>{setSubsbjName(data.value);
                  console.log(subsbjName);
              
               }}
                />

                <Form.Button color='green' content='Add subsubject' onClick={()=>{
                var subjectSubsubject={
                  subject:subject,
                  subsubjectName:subsbjName
                }
                  
                SubjectService.addSubsubjectToSubject(subjectSubsubject).then(result=>{
                  console.log(result.data);
                if(result.data.success){
                  setSubsubjectSubmitSuccess(true);
                  setSubsubjectSubmitError(false);
                }
                else{
                  setSubsubjectSubmitSuccess(false);
                  setSubsubjectSubmitError(true);
                  setErrorSubsubjectSubmitDescription(result.data.message);
                }
               
              })
            }}/>
            <br/>
            
            </Form.Group>
            </Form>
            <Button color='red' className='mb-3' onClick={()=>{handleDeleteSubject(subject.subjectName)}}>
                Delete {subject.subjectName} Subject
              </Button>
            
      

            <br/>
            <Container>
            {subsubjectSubmitSuccess && !subsubjectSubmitError && (
                <Message
                  positive
                  header={"Subsubject has been added succesfully."}
                />
              )}
              {subsubjectSubmitError && !subsubjectSubmitSuccess && (
                <Message 
                  negative
                  header={errorSubsubjectSubmitDescription}
                />  
              )}
              </Container>
           
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

    SubjectService.addSubject(subject).then(result => {
      if(result.data.success){
        setSubmitSuccess(true);
        setSubmitError(false);
      }
      else{
        setSubmitError(true);
        setSubmitSuccess(false);
        setErrorDescription(result.data.message)
      }
    })
  };



  return (
    <Container className="mt-5">
      <Grid celled="internally" verticalAlign="top" centered>
        <Grid.Row columns={2}>
          <Grid.Column textAlign="center" width={9}>
            <Accordion defaultActiveIndex={0} panels={rootPanels} styled/>
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
              <Button color='green' type="submit">Submit</Button>
              {submitSuccess && !submitError && (
                <Message
                  positive
                  header={"Subject has been added succesfully."}
                />
              )}
              {submitError && !submitSuccess && (
                <Message
                  negative
                  header={errorDescription}
                />
              )}
            </Form>
          </Grid.Column>
        </Grid.Row>
      </Grid>
    </Container>
  );
}
