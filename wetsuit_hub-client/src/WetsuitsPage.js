import React from "react";
import WetsuitCard from "./WetsuitCard";
import {Button, CardGroup, Grid, GridColumn, GridRow, Segment} from "semantic-ui-react";
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
                            <WetsuitCard wetsuit={wetsuit}/>
            )
        }

    }

    return(
        <div>
            <button onClick={scrapeNewWetsuits}
                    className={window.location.pathname === "/wetsuits" ? "ui animated  active button" : "ui animated  button"}>
                <div className="visible content">Click Here to Refresh Suits</div>
                <div className="hidden content">
                    <i className="refresh icon"></i>
                </div>
            </button>
            <p/>
            <CardGroup itemsPerRow={5} stackable={true} doubling={true}>
                    {outputList()}
            </CardGroup>
        </div>
    )
}