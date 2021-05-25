import React from "react";
import WetsuitCard from "./WetsuitCard";
import {Grid, GridColumn} from "semantic-ui-react";

export default function WetsuitsPage(){

    const wetsuits = [{
        "name":"O'Neill HyperFreak",
        "Price": "£235",
        "webAddress": "https://www.youtube.com/watch?v=K4P7EML-H4U&ab_channel=MedSchoolInsidersMedSchoolInsiders",
        "size": "M",
        "id": "22710dbf-3fc2-4f7c-978d-0d2bdb85b825"
    }
        ,{
            "name":"O'Neill Psycho",
            "Price": "£245",
            "webAddress": "https://www.youtube.com/watch?v=K4P7EML-H4U&ab_channel=MedSchoolInsidersMedSchoolInsiders",
            "size": "L",
            "id": "8e257cde-f59a-4b7b-b807-e8ca400f8339"
        }
        ,{
            "name":"RipCurl Flashbomb",
            "Price": "£235",
            "webAddress": "https://www.youtube.com/watch?v=K4P7EML-H4U&ab_channel=MedSchoolInsidersMedSchoolInsiders",
            "size": "MS",
            "id": "d2c76284-b95e-42ca-bd70-5021793cf8a3"
        }]

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