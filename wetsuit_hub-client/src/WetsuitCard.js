import {Divider, Card, Icon, Image} from "semantic-ui-react";
import "semantic-ui-css/semantic.min.css";

export default function WetsuitCard({wetsuit, icon}) {

    function sizeFunc(){
        if(wetsuit.size===null){
            return "Sizes Unknown";
        } else{
            return wetsuit.size;
        }

    }

    return (

        <Card fluid key = {wetsuit.id} href={wetsuit.webAddress} target = '_blank'>
            <Image className= "WetsuitTile" src={wetsuit.imageAddress} alt = {wetsuit.name} />
            <Card.Content>
                <Card.Description textAlign={"center"}>{wetsuit.brand}</Card.Description>

                <Divider/>

                <Card.Header textAlign={"center"}>{wetsuit.name}</Card.Header>

                <Divider/>

                <Card.Header textAlign={"center"}>
                    {wetsuit.price=="0"? "Price Unavailable":`£${wetsuit.price}`}
                </Card.Header>
            </Card.Content>

            <Card.Content extra>
                <p>
                    <Icon name = {icon} />
                    {"Sizes: " + sizeFunc()}
                </p>
            </Card.Content>
        </Card>
    )
}