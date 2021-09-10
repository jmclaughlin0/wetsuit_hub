import React, {useEffect, useState} from "react";
import WetsuitCard from "./WetsuitCard";
import {Button, CardGroup, Header, Icon} from "semantic-ui-react";
import {useDispatch, useSelector} from "react-redux";
import {
    fetchKidsWetsuits,
    fetchMensWetsuits,
    fetchWetsuits,
    fetchWomensWetsuits,
    scrapeWetsuits,
    selectWetsuits
} from "./wetsuitsSlice";
import WetsuitSearchBar from "./WetsuitSearchBar";

import {Link} from "react-router-dom";

export default function WetsuitsPage({gender}){

    const wetsuits = useSelector(selectWetsuits)

    const dispatch = useDispatch();

    const [currentPage, setCurrentPage] = useState(gender)

    const  title = gender;

    useEffect(() => {
        if(title === "Mens"){
            dispatch(fetchMensWetsuits())
        }else if (title === "Womens"){
            dispatch(fetchWomensWetsuits())
        }else if (title === "Kids"){
            dispatch(fetchKidsWetsuits())
        }else{
            dispatch(fetchWetsuits());
        }
    },[window.location.pathname])

    function scrapeNewWetsuits()  {
        dispatch(scrapeWetsuits())
    }

    function outputList() {
        if(wetsuits!=null){
            return wetsuits.map(wetsuit =>
                            <WetsuitCard key = {wetsuit.id} wetsuit={wetsuit}/>
            )
        }

    }

    function updateCurrentPage() {
        setCurrentPage(window.location.pathname);
    }

    return(
        <p>
            <Header color={"blue"}>{title + " Wetsuits"}</Header>
            <Button onClick={scrapeNewWetsuits}
                    className={window.location.pathname === "/wetsuits" ? "ui animated  active button" : "ui animated  button"}>
                <div className="visible content">Click Here to Refresh Suits</div>
                <div className="hidden content">
                    <i className="refresh icon"></i>
                </div>
            </Button>

            <Link to="/wetsuits-mens">
                <Button onClick={updateCurrentPage} >
                    Mens Wetsuits <Icon name={"male"}></Icon>
                </Button>
            </Link>
            <Link to="/wetsuits-womens">
                <Button onClick={updateCurrentPage} >
                    Womens Wetsuits <Icon name={"female"}></Icon>
                </Button>
            </Link>
            <Link to="/wetsuits-kids">
                <Button onClick={updateCurrentPage} >
                    Kids Wetsuits <Icon name={"child"}></Icon>
                </Button>
            </Link>


            <p/>
            <WetsuitSearchBar/>
            <p/>
            <CardGroup itemsPerRow={5} stackable={true} doubling={true}>
                    {outputList()}
            </CardGroup>
        </p>
    )
}