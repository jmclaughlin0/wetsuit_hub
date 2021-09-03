import './App.css';
import {Button, Icon} from "semantic-ui-react";
import {Link} from "react-router-dom";
import React, {useState} from "react";

export default function Home() {

    const [currentPage, setCurrentPage] = useState("")


    const updateCurrentPage = () => {
        setCurrentPage(window.location.pathname);
    }

    return (
        <div className="App">
            <header className="App-header">
                <Icon name={"universal access"} size={"massive"} className="App-logo" alt="logo" />
                <p>
                    The Wetsuit Hub
                </p>

                <Link to="/wetsuits">
                    <Button onClick={updateCurrentPage} class={window.location.pathname === "/wetsuits" ? "ui animated  active button" : "ui animated  button"}>
                        Find the Best Prices on Wetsuits from Around the Web
                       <Icon name={"right arrow"}></Icon>
                    </Button>
                </Link>

            </header>

            <p>
                Built by Surfers for Surfers
            </p>
        </div>
    );
}