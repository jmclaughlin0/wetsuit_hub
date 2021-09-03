import React, {useState} from "react";
import WetsuitCard from "./WetsuitCard";
import {Button, CardGroup, Header, Icon} from "semantic-ui-react";
import {useDispatch, useSelector} from "react-redux";
import {scrapeWetsuits, selectWetsuits} from "./wetsuitsSlice";
import WetsuitSearchBar from "./WetsuitSearchBar";
import SizePopup from "./SizePopup";

export default function WetsuitsPage(){

    const [title, setTitle] = useState("All")

    const wetsuits = useSelector(selectWetsuits)

    const dispatch = useDispatch();

    function scrapeNewWetsuits()  {
        dispatch(scrapeWetsuits())
    }

    function outputList() {
        if(wetsuits!=null){
            return wetsuits.map(wetsuit =>
                            <WetsuitCard wetsuit={wetsuit}/>
            )
        }

    }

    function heading(gender){
        setTitle(gender)
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

            <SizePopup gender={"Mens"} onChange = {heading}/>
            <SizePopup gender={"Womens"} onChange = {heading}/>
            <SizePopup gender={"Kids"} onChange = {heading}/>


            <p/>
            <WetsuitSearchBar/>
            <p/>
            <CardGroup itemsPerRow={5} stackable={true} doubling={true}>
                    {outputList()}
            </CardGroup>
        </p>
    )
}