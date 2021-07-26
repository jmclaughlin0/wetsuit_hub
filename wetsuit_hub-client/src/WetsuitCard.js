import {Segment, SegmentGroup, Divider, Image, Header} from "semantic-ui-react";
import "semantic-ui-css/semantic.min.css";

export default function WetsuitCard({wetsuit}) {

    return (
        <div>
            <SegmentGroup>
                <Segment align='middle'>
                    <Image fluid src = {wetsuit.imageAddress} href= {wetsuit.webAddress} target='_blank' />
                    <Divider/>
                    <Header style={{fontWeight: 'bold'}}>{wetsuit.name}</Header>
                    <Header style={{fontWeight: 'bold'}}>{"£" + wetsuit.price}</Header>
                    <Header style={{fontWeight: 'bold'}}>{wetsuit.thickness}</Header>

                </Segment>
            </SegmentGroup>
        </div>
    )
}