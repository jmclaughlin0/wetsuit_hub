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

export default function WetsuitsPage(){

    const path1 = window.location.pathname.split("/")[2]

    const path2 = window.location.pathname.split("/")[3]

    const gender = path1===undefined? "":path1

    const thickness = path2===undefined? "":path2.replace("%20", " ")

    const wetsuits = useSelector(selectWetsuits)

    const numberPages = useSelector(selectPages)

    const search = useSelector(selectSearch)

    const dispatch = useDispatch();

    const title = `${gender} ${thickness}`

    const[zipper, setZipper] = useState("");

    const [pageNumber, setPageNumber] = useState("1")

    const [icon, setIcon] = useState("universal access")

    const [order, setOrder] = useState({key: 'PA', icon: 'sort numeric down', text: 'Price Low - High', value: 'PA' })

    const [colour, setColour] = useState("teal")

    useEffect(()=>{
        dispatch(fetchWetsuits(`${gender}/${thickness}/${zipper}/${pageNumber}/${order.value}/${search}`))
    },[gender, thickness, zipper,pageNumber, order, search, dispatch])

    useEffect(()=>{
        dispatch(fetchNumberPages(`${gender}/${thickness}/${zipper}/${search}`))
    },[gender, thickness, zipper, search, dispatch])

    useEffect(()=>{
        setPageNumber("1")
    },[gender, thickness, zipper, window.location.pathname])


    function scrapeNewWetsuits()  {
        dispatch(scrapeWetsuits())
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
            setColour("black")
        }else if (gender === "Womens"){
            setIcon("female")
            setColour("red")
        }else if (gender === "Kids"){
            setIcon("child")
            setColour("yellow")
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

    return(
        <p>
            <p
               style={{
                   backgroundImage: `url("https://scontent.fbhx1-1.fna.fbcdn.net/v/t1.6435-9/149402941_10225909502744150_6957864331900574952_n.jpg?_nc_cat=102&ccb=1-5&_nc_sid=730e14&_nc_ohc=977rgDkYDi0AX-40Ckb&tn=VX02oLL6cu26lec7&_nc_ht=scontent.fbhx1-1.fna&oh=f5e58e9645ed7e0d00331344329ca06f&oe=61C7A649")`,
                   opacity: "90%",
                   backgroundRepeat: "no-repeat",
                   backgroundSize: "100% auto",
                   backgroundPosition: "bottom",
            }}>
                <Button onClick={scrapeNewWetsuits}
                        className={window.location.pathname === "/wetsuits" ? "ui animated  active button" : "ui animated  button"}>
                    <div className="visible content">Click Here to Refresh Suits</div>
                    <div className="hidden content">
                        <i className="refresh icon"/>
                    </div>
                </Button>

                <Header as='h1' icon color={"black"} textAlign='center'>
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
            <p className="WetsuitList">
            <Segment.Inline >
            <Grid textAlign={"center"}>
                <GridRow >
                    <WetsuitSearchBar/>

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
            </p>
            <p align={"right"}>
                Background Images By Tegan Ward
            </p>
        </p>
    )
}