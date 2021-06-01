import './App.css';
import React from 'react';
import {BrowserRouter as Router, Link, Route, Switch} from "react-router-dom";
import WetsuitsPage from "./WetsuitsPage";
import Home from "./Home";
import {useDispatch} from "react-redux";
import {fetchWetsuits} from "./wetsuitsSlice";


export default function App() {
    const dispatch = useDispatch();
    dispatch(fetchWetsuits());
  return (
      <Router>
          <div>
              <nav>
                  <ul>
                      <li>
                          <Link to="/">Home</Link>
                      </li>
                      <li>
                          <Link to="/wetsuits">Wetsuits Page</Link>
                      </li>
                  </ul>
              </nav>
              <Switch>
                  <Route path="/wetsuits">
                      <WetsuitsPage />
                  </Route>
                  <Route path="/">
                      <Home />
                  </Route>
              </Switch>
          </div>
      </Router>
  )
}
