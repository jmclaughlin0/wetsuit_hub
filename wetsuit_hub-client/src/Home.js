import './App.css';
import {Button, Grid, Header, Icon, Image, Message, Segment} from "semantic-ui-react";
import {Link} from "react-router-dom";
import React, {useState} from "react";
import DevAndPhotogTile from "./DevAndPhotogTile";

export default function Home() {

    const [currentPage, setCurrentPage] = useState("")


    const updateCurrentPage = () => {
        setCurrentPage(window.location.pathname);
    }

    const webpages = [
        {name:"Surf Dome", image:"https://www.surfdome.com/images/surfdome/surfdome-logo-no-tagline.png"},
        {name:"Wetsuit Center", image:"https://www.wetsuitcentre.co.uk/media/logo/stores/1/wetsuit_logo.png"},
        {name:"Wetsuit Outlet", image:"https://cdn.wetsuitoutlet.co.uk/images/design/wso/logo_wso_web.svg"},
        {name:"Blue Tomato", image:"https://www.blue-tomato.com/_ui/bto/images/bt-logo.svg"},
        {name:"need essentials", image:"https://cdn.shopify.com/s/files/1/0004/3719/0666/files/needessentials-logo-150px_150x_f8f39494-c24b-47e1-958b-c61e1d8f7bb2_150x.png?v=1617102785"},
        {name:"Tiki", image:"https://cdn.shopify.com/s/files/1/0601/0716/1785/files/Website_Logo_628d52d7-ce1e-4782-a55d-8d20b11fe28f_320x.png?v=1642610360"},
        {name:"Sorted Surf Shop", image:"https://www.sortedsurfshop.co.uk/media/logo/stores/4/sortedsurflogo.png"},
    ]

    function outputList() {
        return webpages.map((page) => (
                <Grid.Column>
                    <Image className={'Home-SourceSites'} size={'medium'} centered src={page.image} alt = {page.name} />
                </Grid.Column>
            ))
    }

    return (
        <div align='center'>
            <div className="Home-header">
                <Message>
                    <Message.Header>Changes in Service</Message.Header>
                    <p>
                        Due to the cost of running this website, the site is being relaunched over the next week.
                        We apologise for any inconvenience.
                    </p>
                </Message>
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
                <Header>And More Coming Soon!!!</Header>
            <br/>
                </Header>
                <div>
            </div>
            </div>
            <Header as='h2' color='black'>
                The Wetsuit Hub finds wetsuits from across the web and lists them all in one site.
                <p/>
                <p/>
                Click on a wetsuit go to its original site.
            </Header>

            <DevAndPhotogTile/>
        </div>
    );
}
