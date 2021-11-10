import {Divider, Card, Icon} from "semantic-ui-react";
import "semantic-ui-css/semantic.min.css";

export default function WetsuitCard({wetsuit}) {

    function sizeFunc(){
        if(wetsuit.size===null){
            return "Sizes Unknown";
        } else{
            return wetsuit.size;
        }

    }

    return (

        <Card fluid raised key = {wetsuit.id} href={wetsuit.webAddress} target = '_blank'>
            <img src={wetsuit.imageAddress} width="250" height="450" alt = {wetsuit.name} />
            <Card.Content>
                <Card.Header textAlign={"center"}>{wetsuit.name}</Card.Header>

                <Divider/>

                <Card.Header textAlign={"center"}>
                    {"£" + wetsuit.price}
                </Card.Header>
            </Card.Content>

            <Card.Content extra>
                <p>
                    <Icon name='male' />
                    {"Sizes: " + sizeFunc()}
                </p>
            </Card.Content>
        </Card>
    )
}