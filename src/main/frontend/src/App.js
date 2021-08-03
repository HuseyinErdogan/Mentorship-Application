import './App.css';
import 'semantic-ui-css/semantic.min.css'
import { Container } from 'semantic-ui-react';
import Navbar from './layouts/Navbar';
import Dashboard from './layouts/Dashboard';
import LoginPage from './pages/LoginPage';

function App() {
  return (
    <div className="App">
          <Navbar/>
          <Container>
             <LoginPage/>
          </Container>
    </div>
  );
}

export default App;
