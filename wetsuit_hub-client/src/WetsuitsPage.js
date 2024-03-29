import React, {useEffect, useState} from "react";
import WetsuitCard from "./WetsuitCard";
import {
    CardGroup,
    Checkbox,
    Grid,
    GridRow,
    Header,
    Icon,
    Segment,
    Pagination,
    Dropdown, Divider, Input, Dimmer, Loader
} from "semantic-ui-react";
import {useDispatch, useSelector} from "react-redux";
import {
    fetchNumberPages,
    fetchWetsuits,
    selectPages, selectUpdateInProgress,
    selectWetsuits, stateOfUpdate
} from "./wetsuitsSlice";
import WetsuitSearchBar from "./WetsuitSearchBar";
import SizePopup from "./SizePopup";
import DevAndPhotogTile from "./DevAndPhotogTile";

import {fakeWetsuitsData} from "./FakeData";
import {Link} from "react-router-dom";

export default function WetsuitsPage(){

    const path1 = window.location.pathname.split("/")[2]

    const path2 = window.location.pathname.split("/")[3]

    const gender = path1===undefined? "":path1

    const thickness = path2===undefined? "":path2.replace("%20", " ")

    // const wetsuits = useSelector(selectWetsuits)

    const wetsuits = fakeWetsuitsData;

    const numberPages = useSelector(selectPages)

    const [search, setSearch] = useState("");

    const [hood, setHood] = useState("");

    const dispatch = useDispatch();

    const title = `${gender} ${thickness}`

    const[zipper, setZipper] = useState("");

    const [pageNumber, setPageNumber] = useState("1")

    const [icon, setIcon] = useState("universal access")

    const [order, setOrder] = useState({key: 'PA', icon: 'sort numeric down', text: 'Price Low - High', value: 'PA' })

    const [sizeSearch, setSizeSearch] = useState("");

    const currentlyUpdating = useSelector(selectUpdateInProgress)

    useEffect(()=>{
        dispatch(fetchWetsuits(`${gender}/${thickness}/${zipper}/${pageNumber}/${order.value}/${search}/${hood}/${sizeSearch}`))
    },[gender, thickness, zipper,pageNumber, order, search, hood, sizeSearch, dispatch])

    useEffect(()=>{
        dispatch(fetchNumberPages(`${gender}/${thickness}/${zipper}/${search}/${hood}/${sizeSearch}`))
    },[gender, thickness, zipper, search, hood, sizeSearch, dispatch])

    useEffect(()=>{
        setPageNumber("1")
    },[gender, thickness, zipper, search, hood, sizeSearch, order])

    useEffect(() => {
        dispatch(stateOfUpdate())
    }, [gender, thickness, zipper, search, pageNumber, hood, sizeSearch, order, dispatch])


    function searchSetter(query){
        setSearch(query)
    }

    function hoodSetter(){
        setHood(hood=== "" ? "true": "")
    }

    function outputList() {
        if(wetsuits.length > 0){
            return wetsuits.map(wetsuit =>
                            <WetsuitCard key = {wetsuit.id} wetsuit={wetsuit} icon={icon}/>
            )
        }else {
            return <Header textAlign={"center"} color={"blue"} icon> <Icon inverted color = "black" name={"frown outline"} size= "massive"/> No Wetsuits On This Page... </Header>
        }

    }

    useEffect(() => {
        if(gender === "Mens"){
            setIcon("male")
        }else if (gender === "Womens"){
            setIcon("female")
        }else if (gender === "Kids"){
            setIcon("child")
        }
    },[gender])

    function zipperSetter(){
        setZipper(zipper=== "" ? "true": "")
    }


    function changePage(event, data) {
        const page = data.activePage
        setPageNumber(page)
    }

    const orderOptions = [
        {key: 'PA', icon: 'sort numeric down', text: 'Price Low -> High', value: 'PA' },
        {key: 'PD', icon: 'sort numeric up', text: 'Price High -> Low', value: 'PD' },
        {key: 'AB', icon: 'sort alphabet down', text: 'Alphabetically', value: 'AB' },
        {key: 'RA', icon: 'sort alphabet up', text: 'Reverse - Alphabetically', value: 'RA' }
    ]

    function changeOrder(d) {
        setOrder(d)
    }

    function handleSizeChange(size) {
        setSizeSearch(size)
    }


    return(
        <p>
            <p className = 'WetsuitPage-header' >
                <Header as='h1' icon inverted color={"white"} textAlign='center'>
                    <Icon circular inverted color={"blue"} name= {icon}/>
                    {title} Wetsuits
                </Header>
                <p/>

                <Grid textAlign={"center"}>
                    <GridRow>
                        <SizePopup gender={"Mens"}/>
                        <SizePopup gender={"Womens"}/>
                        <SizePopup gender={"Kids"}/>
                    </GridRow>
                    <GridRow>
                        <Segment vertical tertiary inverted color={'light blue'} circular  >
                            <Checkbox toggle label= "Chest Zip or Zipperless Only" onChange={zipperSetter}/>
                            <Divider />
                            <Checkbox toggle label= "Hooded Suits Only" onChange={hoodSetter}/>
                        </Segment>
                    </GridRow>
                    <Segment vertical>
                        <Pagination
                            activePage={pageNumber}
                            firstItem={{ content: <Icon name='angle double left' />, icon: true }}
                            lastItem={{ content: <Icon name='angle double right' />, icon: true }}
                            prevItem={{ content: <Icon name='angle left' />, icon: true }}
                            nextItem={{ content: <Icon name='angle right' />, icon: true }}
                            totalPages={numberPages}
                            onPageChange= { (event, data) => changePage(event,data)} />
                    </Segment>
                </Grid>
            </p>
            <p/>
            <p className="WetsuitList">
            <Segment.Inline>
            <Grid textAlign={"center"}>
                <GridRow >
                    <WetsuitSearchBar onChange = {searchSetter}/>
                    <div>
                        <Input type='text' placeholder = "Search for a size e.g 8" icon ='search' onChange={(event)=>handleSizeChange(event.target.value)}/>
                    </div>
                    <Dropdown
                            button
                            className='icon'
                            icon="arrow down"
                            floating
                            labeled
                            defaultValue={orderOptions[0].value}
                            options = {orderOptions}
                            onChange={(event,data) => changeOrder(data)}
                    />
                </GridRow>
            </Grid>
            </Segment.Inline>
            <p/>
            <CardGroup className= "WetsuitPageSpace" itemsPerRow={5} stackable={true} doubling={true}>
                {outputList()}
            </CardGroup>

                <Segment textAlign={'center'} vertical>
                    <Pagination
                        activePage={pageNumber}
                        firstItem={{ content: <Icon name='angle double left' />, icon: true }}
                        lastItem={{ content: <Icon name='angle double right' />, icon: true }}
                        prevItem={{ content: <Icon name='angle left' />, icon: true }}
                        nextItem={{ content: <Icon name='angle right' />, icon: true }}
                        totalPages={numberPages}
                        onPageChange= { (event, data) => changePage(event,data)} />
                </Segment>

            </p>
            <DevAndPhotogTile/>
            <br/>
        </p>
    )
}