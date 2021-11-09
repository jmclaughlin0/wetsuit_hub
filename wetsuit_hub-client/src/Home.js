import './App.css';
import {Button, Header, Icon} from "semantic-ui-react";
import {Link} from "react-router-dom";
import React, {useState} from "react";

export default function Home() {

    const [currentPage, setCurrentPage] = useState("")


    const updateCurrentPage = () => {
        setCurrentPage(window.location.pathname);
    }

    return (
        <div align='center'>
        <div className="Home-header" style={{
            margin: "auto",
            opacity: 0.9,
            backgroundImage: `url(https://scontent-lhr8-1.xx.fbcdn.net/v/t1.6435-9/245640659_10227725807590636_605473291792221862_n.jpg?_nc_cat=107&ccb=1-5&_nc_sid=8bfeb9&_nc_ohc=pLVr60Wuxs4AX9DWZc2&_nc_ht=scontent-lhr8-1.xx&oh=aa87c5c4dab51bbad2dfbcd9fdb68f40&oe=61ABCAA0)`,
            backgroundSize: '1200px 600px'
        }}>
            <Header className= 'App-header' >
                <div>
                    <Header as = 'h1' inverted color='yellow'  >
                        The Wetsuit Hub
                    </Header>

                    <Link to="/wetsuits/">
                        <Button onClick={updateCurrentPage} class={window.location.pathname === "/wetsuits" ? "ui animated  active button" : "ui animated  button"}>
                            Find the Best Prices on Wetsuits from Around the Web
                           <Icon name={"right arrow"}/>
                        </Button>
                    </Link>
                </div>
            </Header>


        </div>
    <p>
        Built by Surfers for Surfers
    </p>
        </div>
    );
}
