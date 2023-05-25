
import './App.css';
import React,{ Component } from 'react';
import { Alert, Button, Form } from 'react-bootstrap';
import './Registration.css'

class Registration extends Component{
  constructor(props){
    super(props);
    this.registrationAlert= React.createRef();
  }
  handleSubmit=event =>{
    event.preventDefault();
    console.log("submit");
    this.registerUser(event.target.username.value, event.target.password.value);
  }

  registerUser(username, password) {
    fetch('http://localhost:8080/users', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        username: username,
        password:password,
      })
    }).then(function(response){
      if(response.status===200){
        this.showRegistrationAlert("Success","User Registered", "You can now log in using your credentials");
      }else if(response.status===422){
        this.showRegistrationAlert("Danger","User already existed", "Please choose a diffrent name.");

      }else {
        this.showRegistrationAlert("Danger","User not registered", "Something went wrong.");
      }
    }).catch(function(error){
      this.showRegistrationAlert("Danger","Something went wrong.");
    })

    
  }
  showRegistrationAlert(variant, heading, message){
    this.registrationAlert.current.setVatiant(variant);
    this.registrationAlert.current.setHeading(heading);
    this.registrationAlert.current.setMessage(message);
    this.registrationAlert.current.setVisible(true);
  }

  render() {
    return (
    <>
    <div className="Register">
      <Form onSubmit={this.handleSubmit}>
        <Form.Group controlId='username' size="lg">
          <Form.Label>UserName</Form.Label>
          <Form.Control autoFocus name="username"/>
        </Form.Group>

        <Form.Group controlId='password' size="lg">
        <Form.Label>Password</Form.Label>
          <Form.Control type="password" name="Password"/>
        </Form.Group>


        <Button block size="lg" type="submit">Register</Button>
      </Form>
    </div>
    <this.registrationAlert ref={this.registrationAlert}/>
    </>
    )

  }
}

export default Registration;
