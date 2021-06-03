import React from "react";
import WetsuitCard from "./WetsuitCard";
import {Grid, GridColumn} from "semantic-ui-react";
import {useSelector} from "react-redux";
import {selectWetsuits} from "./wetsuitsSlice";

export default function WetsuitsPage(){

    const wetsuits = useSelector(selectWetsuits)

    return(
        <div>
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