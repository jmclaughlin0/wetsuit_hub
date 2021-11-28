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
            backgroundImage: `url(https://scontent-lhr8-1.xx.fbcdn.net/v/t1.6435-9/245640659_10227725807590636_605473291792221862_n.jpg?_nc_cat=107&ccb=1-5&_nc_sid=8bfeb9&_nc_ohc=pLVr60Wuxs4AX9DWZc2&_nc_ht=scontent-lhr8-1.xx&oh=aa87c5c4dab51bbad2dfbcd9fdb68f40&oe=61ABCAA0)`,
            backgroundSize: '100% auto',
            backgroundRepeat: "no-repeat"
        }}>
            <Header className= 'App-header' >
                <div>
                    <Header style={{fontSize: "100px"}} inverted color='white'  >
                        theWetsuitHub.co.uk
                    </Header>

                    <Link to="/wetsuits">
                        <Button inverted color={"orange"} onClick={updateCurrentPage}>
                            Find the Best Prices on Wetsuits from Around the Web
                           <Icon name={"right arrow"}/>
                        </Button>
                    </Link>
                </div>
            </Header>
            <p> Websites That We list: </p>
            <Grid columns = {3} divided textAlign={"center"}>
                {outputList()}
            </Grid>
        </div>
    <p>
        Background Images By Tegan Ward
    </p>
        </div>
    );
}
