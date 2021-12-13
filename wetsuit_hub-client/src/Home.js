import './App.css';
import {Button, CardGroup, Grid, Header, Icon, Segment} from "semantic-ui-react";
import {Link} from "react-router-dom";
import React, {useState} from "react";
import WetsuitCard from "./WetsuitCard";

export default function Home() {

    const [currentPage, setCurrentPage] = useState("")


    const updateCurrentPage = () => {
        setCurrentPage(window.location.pathname);
    }

    const webpages = [
        {name:"Blue Tomato", image:""},
        {name:"need essentials", image:""},
        {name:"Sorted Surf Shop", image:""},
        {name:"Surf Dome", image:""},
        {name:"Tiki", image:""},
        {name:"Wetsuit Center", image:""},
        {name:"Wetsuit Outlet", image:""}
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
            backgroundImage: `url(https://scontent.fbhx1-1.fna.fbcdn.net/v/t39.30808-6/245527768_10227725807350630_7720546953824215513_n.jpg?_nc_cat=106&ccb=1-5&_nc_sid=8bfeb9&_nc_ohc=xSPJUlnGlOwAX8KRQWL&_nc_ht=scontent.fbhx1-1.fna&oh=a7abbbe598fba800e64abb6e9e743c24&oe=61BC8F8F)`,
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
