import {Segment, SegmentGroup, Divider, Image, Header, Card} from "semantic-ui-react";
import "semantic-ui-css/semantic.min.css";

export default function WetsuitCard({wetsuit}) {

    return (
        <Card key = {wetsuit.id} fluid raised image={wetsuit.imageAddress} href={wetsuit.webAddress} target = '_blank'
              header= {wetsuit.name}
              meta={'Available Sizes: '}
              description={'£' + wetsuit.price}>
        </Card>
    )
}