import React, {useEffect, useState} from "react";
import WetsuitCard from "./WetsuitCard";
import {Button, CardGroup, Checkbox, Grid, GridRow, Header, Icon, Segment, Pagination} from "semantic-ui-react";
import {useDispatch, useSelector} from "react-redux";
import {
    fetchWetsuits,
    scrapeWetsuits,
    selectWetsuits
} from "./wetsuitsSlice";

import WetsuitSearchBar from "./WetsuitSearchBar";
import SizePopup from "./SizePopup";

export default function WetsuitsPage({sex, chubb}){

    const wetsuits = useSelector(selectWetsuits)

    const dispatch = useDispatch();

    const title = `${sex} ${chubb}`

    const[zipper, setZipper] = useState("");


    useEffect(()=>{
        dispatch(fetchWetsuits(`${sex}/${chubb}/${zipper}`))
    },[sex, chubb, zipper, dispatch])



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

    const [icon, setIcon] = useState("universal access")

    useEffect(() => {
        let gender = sex

        if(gender === "Mens"){
            setIcon("male")
        }else if (gender === "Womens"){
            setIcon("female")
        }else if (gender === "Kids"){
            setIcon("child")
        }
    },[sex])

    function zipperSetter(){
        setZipper(zipper===""? "true": "")
    }


    return(
        <p>
            <p className= 'Wetsuit-header' style={{
                margin: "auto",
                opacity: 0.9,
                backgroundImage: `url(https://scontent-lhr8-1.xx.fbcdn.net/v/t1.6435-9/186411367_10158563070803780_6241247215245455377_n.jpg?_nc_cat=100&ccb=1-5&_nc_sid=730e14&_nc_ohc=dMhnx1KvcX4AX9ESYYw&_nc_ht=scontent-lhr8-1.xx&oh=09e07cc0d202ee2964cadf80fbb41ad4&oe=61ADA286)`,
                backgroundSize: '1400px 500px',

            }}>
                <Button onClick={scrapeNewWetsuits}
                        className={window.location.pathname === "/wetsuits" ? "ui animated  active button" : "ui animated  button"}>
                    <div className="visible content">Click Here to Refresh Suits</div>
                    <div className="hidden content">
                        <i className="refresh icon"/>
                    </div>
                </Button>

                <Header as='h2' icon textAlign='center'>
                    <Icon name= {icon} circular />
                    {title} Wetsuits
                </Header>
                <p/>

                <Grid textAlign={"center"}>
                    <GridRow>
                        <SizePopup gender={"Mens"}/>
                        <SizePopup gender={"Womens"}/>
                        <SizePopup gender={"Kids"}/>
                    </GridRow>
                    <GridRow>
                        <Segment vertical tertiary inverted color={'light blue'} circular  >
                            <Checkbox toggle label= "Chest Zip or Zipperless Only" onChange={zipperSetter}/>
                        </Segment>
                    </GridRow>
                    <Segment vertical>
                        <Pagination defaultActivePage={5} totalPages={10} />
                    </Segment>
                </Grid>
            </p>
            <p/>
            <WetsuitSearchBar/>
            <p/>

            <CardGroup itemsPerRow={5} stackable={true} doubling={true}>
                    {outputList()}
            </CardGroup>
        </p>
    )
}