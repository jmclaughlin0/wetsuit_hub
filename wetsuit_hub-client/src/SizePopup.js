import {Button, Popup, Grid, Icon} from "semantic-ui-react";
import {useEffect, useState} from "react";

export default function SizePopup({gender, onChange}) {

    const [icon, setIcon] = useState("")

    useEffect(() => {
        if(gender === "Mens"){
            setIcon("male")
        }else if (gender === "Womens"){
            setIcon("female")
        }else if (gender === "Kids"){
            setIcon("child")
        }
    },[gender])

    function getGenderWetsuits(thickness) {
        onChange(gender, thickness)
    }

    return(
            <Popup trigger={<Button onClick={()=>getGenderWetsuits("")}>{gender + " Wetsuits "}<Icon name={icon}/></Button>} flowing hoverable>
                <Grid centered divided columns={5}>
                    <Grid.Column textAlign='center'>
                        <Button onClick={()=>getGenderWetsuits("6 mm")}>6 mm</Button>
                    </Grid.Column>
                    <Grid.Column textAlign='center'>
                        <Button onClick={()=>getGenderWetsuits("5 mm")}>5 mm</Button>
                    </Grid.Column>
                    <Grid.Column textAlign='center'>
                        <Button onClick={()=>getGenderWetsuits("4 mm")}>4 mm</Button>
                    </Grid.Column>
                    <Grid.Column textAlign='center'>
                        <Button onClick={()=>getGenderWetsuits("3 mm")}>3 mm</Button>
                    </Grid.Column>
                    <Grid.Column textAlign='center'>
                        <Button onClick={()=>getGenderWetsuits("2 mm")}>2 mm</Button>
                    </Grid.Column>
                </Grid>
            </Popup>
        )
}