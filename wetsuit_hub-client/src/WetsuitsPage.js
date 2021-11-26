import React, {useEffect, useState} from "react";
import WetsuitCard from "./WetsuitCard";
import {
    Button,
    CardGroup,
    Checkbox,
    Grid,
    GridRow,
    Header,
    Icon,
    Segment,
    Pagination,
    Dropdown
} from "semantic-ui-react";
import {useDispatch, useSelector} from "react-redux";
import {
    fetchNumberPages,
    fetchWetsuits,
    scrapeWetsuits,
    selectPages,
    selectWetsuits,
    selectSearch
} from "./wetsuitsSlice";
import WetsuitSearchBar from "./WetsuitSearchBar";
import SizePopup from "./SizePopup";

export default function WetsuitsPage({sex, chubb}){

    const wetsuits = useSelector(selectWetsuits)

    const numberPages = useSelector(selectPages)

    const search = useSelector(selectSearch)

    const dispatch = useDispatch();

    const title = `${sex} ${chubb}`

    const[zipper, setZipper] = useState("");

    const [pageNumber, setPageNumber] = useState("1")

    const [icon, setIcon] = useState("universal access")

    const [order, setOrder] = useState({key: 'PA', icon: 'sort numeric down', text: 'Price Low - High', value: 'PA' })


    useEffect(()=>{
        dispatch(fetchWetsuits(`${sex}/${chubb}/${zipper}/${pageNumber}/${order.value}/${search}`))
    },[sex, chubb, zipper,pageNumber, order, search, dispatch])

    useEffect(()=>{
        dispatch(fetchNumberPages(`${sex}/${chubb}/${zipper}/${search}`))
    },[sex, chubb, zipper, search, dispatch])

    useEffect(()=>{
        setPageNumber("1")
    },[sex, chubb, zipper])


    function scrapeNewWetsuits()  {
        dispatch(scrapeWetsuits())
    }

    function outputList() {
        if(wetsuits.length > 0){
            return wetsuits.map(wetsuit =>
                            <WetsuitCard key = {wetsuit.id} wetsuit={wetsuit}/>
            )
        }else {
            return <Header textAlign={"center"} color={"blue"} icon> <Icon inverted color = "black" name={"frown outline"} size= "massive"/> No Wetsuits On This Page... </Header>
        }

    }

    useEffect(() => {
        let gender = sex

        if(gender === "Mens"){
            setIcon("male")
        }else if (gender === "Womens"){
            setIcon("female")
        }else if (gender === "Kids"){
            setIcon("child")
        }
    },[sex])

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

    return(
        <p>
            <p className= 'Wetsuit-header' style={{
                margin: "auto",
                opacity: 0.9,
                backgroundImage: `url(https://scontent-lhr8-1.xx.fbcdn.net/v/t1.6435-9/186411367_10158563070803780_6241247215245455377_n.jpg?_nc_cat=100&ccb=1-5&_nc_sid=730e14&_nc_ohc=dMhnx1KvcX4AX9ESYYw&_nc_ht=scontent-lhr8-1.xx&oh=09e07cc0d202ee2964cadf80fbb41ad4&oe=61ADA286)`,
                backgroundSize: '1400px 500px',

            }}>
                <Button onClick={scrapeNewWetsuits}
                        className={window.location.pathname === "/wetsuits" ? "ui animated  active button" : "ui animated  button"}>
                    <div className="visible content">Click Here to Refresh Suits</div>
                    <div className="hidden content">
                        <i className="refresh icon"/>
                    </div>
                </Button>

                <Header as='h2' icon textAlign='center'>
                    <Icon name= {icon} circular />
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
                        </Segment>
                    </GridRow>
                    <Segment vertical>
                        <Pagination
                            defaultActivePage={1}
                            ellipsisItem={{ content: <Icon name='ellipsis horizontal' />, icon: true }}
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
            <Segment.Inline >
            <Grid textAlign={"center"}>
                <GridRow >
                    <WetsuitSearchBar/>

                    <Dropdown
                            button
                            className='icon'
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

            <CardGroup itemsPerRow={5} stackable={true} doubling={true}>
                    {outputList()}
            </CardGroup>
        </p>
    )
}