import React from 'react';
import {Input} from "semantic-ui-react";

export default function WetsuitSearchBar({onChange}) {

    const handleChange = (event) => {
        onChange(event.target.value)
    }

    return (
        <div>
            <Input type='text' placeholder = "Search for a wetsuit..." icon='search' onChange={handleChange}/>
        </div>
    )
}