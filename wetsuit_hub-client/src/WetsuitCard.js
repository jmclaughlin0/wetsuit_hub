import {Segment, SegmentGroup, Divider, Image, Header, Card, Icon} from "semantic-ui-react";
import "semantic-ui-css/semantic.min.css";

export default function WetsuitCard({wetsuit}) {

    return (

        <Card fluid raised key = {wetsuit.id} href={wetsuit.webAddress} target = '_blank'>
            <img src={wetsuit.imageAddress} wrapped ui={false} width="270" height="300" />
            <Card.Content>
                <Card.Header textAlign={"center"}>{wetsuit.name}</Card.Header>

                <Divider/>

                <Card.Header textAlign={"center"}>
                    {"£" + wetsuit.price}
                </Card.Header>
            </Card.Content>

            <Card.Content extra>
                <a>
                    <Icon name='male' />
                    {"Sizes: " + wetsuit.size}
                </a>
            </Card.Content>
        </Card>
    )
}