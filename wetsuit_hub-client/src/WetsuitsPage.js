import React, {useEffect, useState} from "react";
import WetsuitCard from "./WetsuitCard";
import {Button, CardGroup, Grid, Header, Icon} from "semantic-ui-react";
import {useDispatch, useSelector} from "react-redux";
import {
    fetchWetsuits,
    scrapeWetsuits,
    selectWetsuits
} from "./wetsuitsSlice";

import WetsuitSearchBar from "./WetsuitSearchBar";
import SizePopup from "./SizePopup";

export default function WetsuitsPage(){

    const wetsuits = useSelector(selectWetsuits)

    const dispatch = useDispatch();

    const [gender, setGender] = useState("")

    const [thickness, setThickness] = useState("")

    const [title, setTitle] = useState("All")

    useEffect(()=>{
        dispatch(fetchWetsuits(`${gender}/${thickness}`))
    },[gender, thickness, dispatch])



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

    function updateWetsuit(gender, thickness){
        setGender(gender)
        setThickness(thickness)
        setTitle(`${gender} ${thickness}`)
    }

    const [icon, setIcon] = useState("universal access")

    useEffect(() => {
        if(gender === "Mens"){
            setIcon("male")
        }else if (gender === "Womens"){
            setIcon("female")
        }else if (gender === "Kids"){
            setIcon("child")
        }
    },[gender])


    return(


        <p>
            <Button onClick={scrapeNewWetsuits}
                    className={window.location.pathname === "/wetsuits" ? "ui animated  active button" : "ui animated  button"}>
                <div className="visible content">Click Here to Refresh Suits</div>
                <div className="hidden content">
                    <i className="refresh icon"></i>
                </div>
            </Button>

            <Header as='h2' icon textAlign='center'>
                <Icon name= {icon} circular />
                {title} Wetsuits
            </Header>
            <p/>


            <Grid textAlign={"center"}>
                <SizePopup gender={"Mens"} onChange={updateWetsuit}/>
                <SizePopup gender={"Womens"} onChange={updateWetsuit}/>
                <SizePopup gender={"Kids"} onChange={updateWetsuit}/>
            </Grid>




            <p/>
            <WetsuitSearchBar/>
            <p/>
            <CardGroup itemsPerRow={5} stackable={true} doubling={true}>
                    {outputList()}
            </CardGroup>
        </p>
    )
}