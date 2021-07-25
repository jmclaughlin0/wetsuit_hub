import React from "react";
import WetsuitCard from "./WetsuitCard";
import {Button, Grid, GridColumn} from "semantic-ui-react";
import {useDispatch, useSelector} from "react-redux";
import {scrapeWetsuits, selectWetsuits} from "./wetsuitsSlice";

export default function WetsuitsPage(){

    const wetsuits = useSelector(selectWetsuits)

    const dispatch = useDispatch();

    function scrapeNewWetsuits()  {
        dispatch(scrapeWetsuits())
    }

    return(
        <div>
            <Button onClick={scrapeNewWetsuits}>Click Here to Refresh Suits</Button>
            <Grid columns = {3} padded>
                {wetsuits.map(wetsuit =>
                        <GridColumn key={wetsuit.id}>
                            <WetsuitCard wetsuit={wetsuit}/>
                        </GridColumn>
                )}
            </Grid>
        </div>
    )
}