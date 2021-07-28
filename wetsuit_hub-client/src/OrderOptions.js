import React from 'react';
import { Dropdown } from "semantic-ui-react";
import "semantic-ui-css/semantic.min.css";
import { setOrder } from './orderSlice';
import { useDispatch } from 'react-redux';


function OrderOptions() {

    const dispatch = useDispatch();

    const handleSelection = e => {
        dispatch(setOrder(e.target.innerText))
    }

    const wetsuitOrderOptions = [
        { key: "price_up", text: 'Price Ascending', value: "price_up" },
        { key: "price_down", text: 'Price Descending', value: "price_down" },
    ]

    return (
        <div>

            <Dropdown placeholder='Choose Wetsuit Order'
                options={wetsuitOrderOptions} static search selection onChange={handleSelection} />
            <p />

        </div>
    );
}

export default OrderOptions;