import './App.css';
import {Button, Grid, Header, Icon, Segment} from "semantic-ui-react";
import {Link} from "react-router-dom";
import React, {useState} from "react";
import DevAndPhotogTile from "./DevAndPhotogTile";

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
            <div className="Home-header">
                <Link to="/wetsuits">
                <Button  color={"orange"} onClick={updateCurrentPage}>
                    Find the Best Prices on Wetsuits from Around the Web
                    <Icon name={"right arrow"}/>
                </Button>
            </Link>
                <Header>
                    <p> Websites That We Source From: </p>
                        <Grid columns = {3} divided textAlign={"center"}>
                            {outputList()}
                        </Grid>
                    <p/>
                <Header size = "huge" >And More Coming Soon!!!</Header>
            <br/>
                </Header>
                <div>
                <Header as='h2' color='black'>
                    The Wetsuit Hub finds wetsuits from across the web and lists them all in one site.
                    <p/>
                    <p/>
                    Click on a wetsuit go to its original site.
                </Header>
            </div>
            </div>
            <DevAndPhotogTile/>
        </div>
    );
}
