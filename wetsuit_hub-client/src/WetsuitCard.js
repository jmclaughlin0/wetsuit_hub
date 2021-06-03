import {Segment, SegmentGroup, Divider, Image, Header} from "semantic-ui-react";
import "semantic-ui-css/semantic.min.css";
import logo from "./logo.svg";
import {wetsuitsPage} from "./URLS";

export default function WetsuitCard({wetsuit}) {

    return (
        <div>
            <SegmentGroup>
                <Segment align='middle'>
                    <Image src={logo}/>
                    <a
                        className="App-link"
                        href= {wetsuit.webAddress}
                        target="_blank"
                        rel="noopener noreferrer"
                    >
                        Click here to see the {wetsuit.name} on its home site...
                    </a>
                    <Divider/>
                    <Header style={{fontWeight: 'bold'}}>{wetsuit.name}</Header>
                    <Header style={{fontWeight: 'bold'}}>{"£" + wetsuit.price}</Header>
                    <Header style={{fontWeight: 'bold'}}>{wetsuit.thickness}</Header>

                </Segment>
            </SegmentGroup>
        </div>
    )
}