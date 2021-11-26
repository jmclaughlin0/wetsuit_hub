import React from 'react';
import {useDispatch, useSelector} from 'react-redux';
import {Input} from "semantic-ui-react";
import {changeSearch, selectSearch} from "./wetsuitsSlice";

export default function WetsuitSearchBar() {
    const dispatch = useDispatch();
    const question = useSelector(selectSearch)

    const handleChange = (event) => {
        dispatch(changeSearch(event.target.value));
    }

    return (
        <div>
            <Input type='text' placeholder = "Search..." icon='search' value = {question} onChange={handleChange}/>
        </div>
    )
}