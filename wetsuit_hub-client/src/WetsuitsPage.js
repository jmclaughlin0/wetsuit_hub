import React from "react";
import WetsuitCard from "./WetsuitCard";
import {Button, Grid, GridColumn} from "semantic-ui-react";
import {useDispatch, useSelector} from "react-redux";
import {scrapeWetsuits, selectWetsuits} from "./wetsuitsSlice";
import OrderOptions from "./OrderOptions";
import {selectOrder} from "./orderSlice";

export default function WetsuitsPage(){

    const wetsuits = useSelector(selectWetsuits)

    const dispatch = useDispatch();

    function scrapeNewWetsuits()  {
        dispatch(scrapeWetsuits())
    }
    
    const selection = useSelector(selectOrder)

    function sorter(selection) {

        let newWetsuits = [...wetsuits];
        const newSelection = selection;

        if(newWetsuits!= null){
            if (newSelection === ("price_down")) {
                newWetsuits = [...wetsuits].reverse()
                }
            }else if (newSelection === ("price_up")) {
                newWetsuits = [...wetsuits]
                }
            return newWetsuits;
        }

    function outputList() {
        var newWetsuits = sorter(selection);

        if(newWetsuits!=null){
            return newWetsuits.map(wetsuit =>
                    <GridColumn key={wetsuit.id}>
                        <WetsuitCard wetsuit={wetsuit}/>
                    </GridColumn>
            )
        }

    }

    return(
        <div>
            <OrderOptions/>
            <Button onClick={scrapeNewWetsuits}>Click Here to Refresh Suits</Button>
            <Grid columns = {5} padded>
                {outputList()}
            </Grid>
        </div>
    )
}