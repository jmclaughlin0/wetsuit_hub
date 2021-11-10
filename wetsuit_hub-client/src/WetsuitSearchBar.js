import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import {getFilteredWetsuits, changeFilter, selectFilter} from "./wetsuitsSlice";
import {Input, Search} from "semantic-ui-react";

function MealsPageFilter() {
    const dispatch = useDispatch();
    const filter = useSelector(selectFilter)

    const handleChange = (event) => {
        dispatch(changeFilter(event.target.value));
        dispatch(getFilteredWetsuits());
    }

    return (
        <div>
            <Input type='text' placeholder = "Search..." icon='search' value={filter} onChange={handleChange}/>
        </div>
    )
}

export default MealsPageFilter;