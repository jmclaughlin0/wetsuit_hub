import './App.css';
import {Button, Grid, Header, Icon} from "semantic-ui-react";
import {Link} from "react-router-dom";
import React, {useState} from "react";

export default function Home() {

    const [currentPage, setCurrentPage] = useState("")


    const updateCurrentPage = () => {
        setCurrentPage(window.location.pathname);
    }

    const webpages = [
        {name:"Surf Dome", image:""},
        {name:"Wetsuit Center", image:""},
        {name:"Wetsuit Outlet", image:""},
        {name:"Blue Tomato", image:""},
        {name:"need essentials", image:""},
        {name:"Sorted Surf Shop", image:""},
        {name:"Tiki", image:""},
    ]

    function outputList() {
        return webpages.map((page) => (
                <Grid.Column>
                    {page.name}
                </Grid.Column>
            ))
    }

    return (
        <div align='center'>
        <div className="Home-header" style={{
            margin: "auto",
            opacity: 0.9,
            backgroundImage: 'image(../public/wetsuit_hub-wetsuitPageImage.png)',
            backgroundSize: '100% auto',
            backgroundRepeat: "no-repeat"
        }}>
            <Header className= 'App-header' >
                <div>
                    <Header style={{fontSize: "100px"}} inverted color='white'  >
                        theWetsuitHub.co.uk
                    </Header>
                    <Header as='h2' inverted color='white'>
                        The Wetsuit Hub finds wetsuits from across the web and lists them all in one site.
                        <p/>
                        <p/>
                        Click on a wetsuit go to its original site.
                    </Header>

                    <Link to="/wetsuits">
                        <Button inverted color={"orange"} onClick={updateCurrentPage}>
                            Find the Best Prices on Wetsuits from Around the Web
                           <Icon name={"right arrow"}/>
                        </Button>
                    </Link>
                </div>
            </Header>
            <p> Websites That We Source From: </p>
            <Grid columns = {3} divided textAlign={"center"}>
                {outputList()}
            </Grid>
            <p/>
            <Header>And More Coming Soon!!!</Header>
        </div>
            <Header as={"h4"}  align={"center"}> Background Images By{" "}
                <a
                    className="App-link"
                    href="https://www.instagram.com/teganwphotography/"
                    target="_blank"
                    rel="noopener noreferrer"
                >
                    Tegan Ward
                </a>
            </Header>
            <br/>
        </div>
    );
}
