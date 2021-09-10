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


    function getMensWetsuits(thickness){
        onChange("Mens")
    }

    function getWomensWetsuits() {
        onChange("Womens")
    }

    function getKidsWetsuits() {
        onChange("Kids")
    }

    function getGenderWetsuits(thickness) {
        if(gender==="Mens"){
            getMensWetsuits(thickness)
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
                        <Button onClick={getGenderWetsuits("5")}>5 mm</Button>
                    </Grid.Column>
                    <Grid.Column textAlign='center'>
                        <Button onClick={getGenderWetsuits}>4 mm</Button>
                    </Grid.Column>
                    <Grid.Column textAlign='center'>
                        <Button onClick={getGenderWetsuits}>3 mm</Button>
                    </Grid.Column>
                    <Grid.Column textAlign='center'>
                        <Button onClick={getGenderWetsuits}>2 mm</Button>
                    </Grid.Column>
                </Grid>
            </Popup>
        )
}