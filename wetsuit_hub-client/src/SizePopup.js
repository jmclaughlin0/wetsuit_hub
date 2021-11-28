import {Button, Popup, Grid, Icon} from "semantic-ui-react";
import React, {useEffect, useState} from "react";
import {changeGender, changeThickness} from "./wetsuitsSlice";
import {useDispatch} from "react-redux";
import {Link} from "react-router-dom";

export default function SizePopup({gender}) {

    const [icon, setIcon] = useState("")

    const [colour, setColour] = useState("")

    const [align, setAlign] = useState("")

    const [currentPage, setCurrentPage] = useState("")

    const dispatch = useDispatch()

    useEffect(() => {
        if(gender === "Mens"){
            setIcon("male")
            setColour("black")
            setAlign("bottom left")
        }else if (gender === "Womens"){
            setIcon("female")
            setColour("red")
            setAlign("bottom center")
        }else if (gender === "Kids"){
            setIcon("child")
            setColour("blue")
            setAlign("bottom right")
        }
    },[gender])

    function getGenderWetsuits(thickness) {
        dispatch(changeGender(gender))
        dispatch(changeThickness(thickness))
        setCurrentPage(window.location.pathname)
    }


    return(
            <Popup position={align} trigger={<Link to = {`/wetsuits/${gender}`}> <Button color={colour} onClick={()=>getGenderWetsuits("")}> {gender + " Wetsuits "} <Icon name={icon}/> </Button></Link>} flowing hoverable>
                <nav>
                <Grid centered divided columns={5}>
                            <Grid.Column textAlign='center'>
                                <Link to= {`/wetsuits/${gender}/6 mm`}>
                                    <Button onClick={()=>getGenderWetsuits("6 mm")}>6 mm</Button>
                                </Link>
                            </Grid.Column>
                            <Grid.Column textAlign='center'>
                                <Link to={`/wetsuits/${gender}/5 mm`}>
                                    <Button onClick={()=>getGenderWetsuits("5 mm")}>5 mm</Button>
                                </Link>
                            </Grid.Column>
                            <Grid.Column textAlign='center'>
                                <Link to={`/wetsuits/${gender}/4 mm`}>
                                    <Button onClick={()=>getGenderWetsuits("4 mm")}>4 mm</Button>
                                </Link>
                            </Grid.Column>
                            <Grid.Column textAlign='center'>
                                <Link to={`/wetsuits/${gender}/3 mm`}>
                                    <Button onClick={()=>getGenderWetsuits("3 mm")}>3 mm</Button>
                                </Link>
                            </Grid.Column>
                            <Grid.Column textAlign='center'>
                                <Link to={`/wetsuits/${gender}/2 mm`}>
                                    <Button onClick={()=>getGenderWetsuits("2 mm")}>2 mm</Button>
                                </Link>
                            </Grid.Column>

                </Grid>
            </nav>
            </Popup>
        )
}