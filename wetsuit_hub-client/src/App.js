import './App.css';
import React, {useState} from 'react';
import {BrowserRouter as Router, Link, Route, Switch} from "react-router-dom";
import WetsuitsPage from "./WetsuitsPage";
import Home from "./Home";
import {useDispatch} from "react-redux";
import {fetchWetsuits} from "./wetsuitsSlice";
import {Container, Menu} from "semantic-ui-react";


export default function App() {

    const dispatch = useDispatch;

    dispatch(fetchWetsuits);

    const [currentPage, setCurrentPage] = useState("")


    const updateCurrentPage = () => {
        setCurrentPage(window.location.pathname);
    }


  return (

      <Router>
          <div style={{marginTop: "0.5em", marginLeft: "0.5em", marginBottom: "-0.5em"}} >
              <nav>
                  {/*<Menu size='large'>*/}
                  {/*    <Container>*/}
                  {/*        <Menu.Item as='a' active>*/}
                  {/*            Home*/}
                  {/*        </Menu.Item>*/}
                  {/*        <Menu.Item as='a'>Work</Menu.Item>*/}
                  {/*        <Menu.Item as='a'>Company</Menu.Item>*/}
                  {/*        <Menu.Item as='a'>Careers</Menu.Item>*/}
                  {/*        <Menu.Item position='right'>*/}
                  {/*            <Button as='a' inverted={!fixed}>*/}
                  {/*                Log in*/}
                  {/*            </Button>*/}
                  {/*            <Button as='a' inverted={!fixed} primary={fixed} style={{ marginLeft: '0.5em' }}>*/}
                  {/*                Sign Up*/}
                  {/*            </Button>*/}
                  {/*        </Menu.Item>*/}
                  {/*    </Container>*/}
                  {/*</Menu>*/}
                  <Link to="/">
                      <button onClick={updateCurrentPage} class={window.location.pathname === "/" ? "ui animated active button" : "ui animated  button"} >
                          <div class="visible content">Home</div>
                          <div class="hidden content">
                              <i class="home icon"></i>
                          </div>
                      </button>
                  </Link>
                  <Link to="/wetsuits">
                      <button onClick={updateCurrentPage} class={window.location.pathname === "/wetsuits" ? "ui animated  active button" : "ui animated  button"}>
                          <div class="visible content">Wetsuits</div>
                          <div class="hidden content">
                              <i class = 'tint icon'></i>
                          </div>
                      </button>
                  </Link>
              </nav>
          </div>
          <div class="ui divider" ></div>

          <Switch>
              <Route path="/wetsuits">
                  <WetsuitsPage/>
              </Route>
              <Route path="/" >
                  <Home />
              </Route>
          </Switch>
      </Router>
  )
}
