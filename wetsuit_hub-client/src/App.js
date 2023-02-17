import './App.css';
import React, {useState} from 'react';
import {BrowserRouter as Router, Link, Route, Switch} from "react-router-dom";
import WetsuitsPage from "./WetsuitsPage";
import Home from "./Home";
import {useDispatch, useSelector} from "react-redux";
import {
    changeGender,
    changeThickness,
    selectGender,
    selectThickness
} from "./wetsuitsSlice";
import {Header, Segment} from "semantic-ui-react";


export default function App() {

    const dispatch = useDispatch();

    const [currentPage, setCurrentPage] = useState("")

    const updateCurrentPage = () => {
        setCurrentPage(window.location.pathname);
    }

    const gender = useSelector(selectGender);

    const thickness = useSelector(selectThickness);

    function resetToAllWetsuits() {
        dispatch(changeGender(""))
        dispatch(changeThickness(""))
    }

    return (

      <Router className={'App'}>
          <div  style={{marginTop: "0.5em", marginLeft: "0.5em", marginBottom: "-0.5em"}} >
              <nav>
                  <Link to="/">
                      <button onClick={updateCurrentPage} color={"black"} class={window.location.pathname === "/" ? "ui animated active button" : "ui animated  button"} >
                          <div class="visible content">Home</div>
                          <div class="hidden content">
                              <i class="home icon"/>
                          </div>
                      </button>
                  </Link>
                  <Link to={`/wetsuits`}>
                      <button onClick={updateCurrentPage + resetToAllWetsuits} color={"blue"} class={window.location.pathname === "/wetsuits" ? "ui animated  active button" : "ui animated  button"}>
                          <div class="visible content">Wetsuits</div>
                          <div class="hidden content">
                              <i class = 'tint icon'/>
                          </div>
                      </button>
                  </Link>
                  <Segment attached={'top'} textAlign={'center'} inverted color={'black'} basic padded = 'very' massive>
                      <Header content = "theWetsuitHub.co.uk" size= "huge" color='black' />
                      <Header size = 'tiny' content = "The price comparison website for wetsuits"/>
                  </Segment>
                  <Segment>
                      <Header color={"red"}>
                          N.B - This is just a mock app. Due to the financial constraints of hosting on AWS, this is a static webpage with none of the original 2 hourly webscraping from source websites, adaptive sorting or other wetsuits apart from Men's 6mm Suits. Apologies to anyone who has being using the site. Full code of the working site can be seen at: <Link>https://github.com/jmclaughlin0/wetsuit_hub</Link>
                      </Header>
                  </Segment>
              </nav>
          </div>
          <div class="ui divider" ></div>

          <Switch>
              <Route path={`/wetsuits${gender!==""? "/" + gender:""}${thickness!==""? "/" + thickness:""}`}>
                  <WetsuitsPage/>
              </Route>
              <Route path={`/`} >
                  <Home />
              </Route>
          </Switch>


      </Router>
  )
}
