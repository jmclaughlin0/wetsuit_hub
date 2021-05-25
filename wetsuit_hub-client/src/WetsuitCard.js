import {Segment, SegmentGroup, Divider, Image, Header} from "semantic-ui-react";
import "semantic-ui-css/semantic.min.css";
import logo from "./logo.svg";

export default function WetsuitCard({wetsuit}) {

    return (
        <div>
            <SegmentGroup>
                <Segment align='middle'>
                    <Image src={logo}/>
                    <Divider/>
                    <Header style={{fontWeight: 'bold'}}>{wetsuit.name}</Header>
                    <Header style={{fontWeight: 'bold'}}>{wetsuit.size}</Header>
                </Segment>
            </SegmentGroup>
        </div>
    )
}