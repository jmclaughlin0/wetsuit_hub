import {Button, Popup, Grid, Icon} from "semantic-ui-react";
import {useEffect, useState} from "react";
import {useDispatch} from "react-redux";
import {fetchKidsWetsuits, fetchMensWetsuits, fetchWomensWetsuits} from "./wetsuitsSlice";

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
    })

    const dispatch = useDispatch();

    function getMensWetsuits(){
        onChange("Mens")
        dispatch(fetchMensWetsuits())
    }

    function getWomensWetsuits() {
        onChange("Womens")
        dispatch(fetchWomensWetsuits())
    }

    function getKidsWetsuits() {
        onChange("Kids")
        dispatch(fetchKidsWetsuits())
    }

    function getGenderWetsuits() {
        if(gender === "Mens"){
            getMensWetsuits()
        }else if (gender === "Womens"){
            getWomensWetsuits()
        }else if (gender === "Kids"){
            getKidsWetsuits()
        }
    }

    return(
            <Popup trigger={<Button onClick={getGenderWetsuits}>{gender + " Wetsuits "}<Icon name={icon}/></Button>} flowing hoverable>
                <Grid centered divided columns={4}>
                    <Grid.Column textAlign='center'>
                        <Button>5 mm</Button>
                    </Grid.Column>
                    <Grid.Column textAlign='center'>
                        <Button>4 mm</Button>
                    </Grid.Column>
                    <Grid.Column textAlign='center'>
                        <Button>3 mm</Button>
                    </Grid.Column>
                    <Grid.Column textAlign='center'>
                        <Button>2 mm</Button>
                    </Grid.Column>
                </Grid>
            </Popup>
        )
}