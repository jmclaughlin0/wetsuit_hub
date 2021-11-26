import './App.css';
import React, {useState} from 'react';
import {BrowserRouter as Router, Link, Route, Switch} from "react-router-dom";
import WetsuitsPage from "./WetsuitsPage";
import Home from "./Home";
import {useDispatch, useSelector} from "react-redux";
import {changeGender, changeThickness, selectGender, selectThickness} from "./wetsuitsSlice";


export default function App() {

    const [currentPage, setCurrentPage] = useState("")

    const updateCurrentPage = () => {
        setCurrentPage(window.location.pathname);
    }

    const gender = useSelector(selectGender);

    const thickness = useSelector(selectThickness);

    const dispatch = useDispatch();


    function resetToAllWetsuits() {
        dispatch(changeGender(""))
        dispatch(changeThickness(""))
    }

    return (

      <Router>
          <div style={{marginTop: "0.5em", marginLeft: "0.5em", marginBottom: "-0.5em"}} >
              <nav>
                  <Link to="/">
                      <button onClick={updateCurrentPage} class={window.location.pathname === "/" ? "ui animated active button" : "ui animated  button"} >
                          <div class="visible content">Home</div>
                          <div class="hidden content">
                              <i class="home icon"></i>
                          </div>
                      </button>
                  </Link>
                  <Link to={`/wetsuits`}>
                      <button onClick={updateCurrentPage + resetToAllWetsuits} class={window.location.pathname === "/wetsuits" ? "ui animated  active button" : "ui animated  button"}>
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
