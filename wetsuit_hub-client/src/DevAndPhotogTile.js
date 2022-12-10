import React from 'react';
import {Grid, Header, Segment} from "semantic-ui-react";

export default function DevAndPhotogTile() {

    return (
        <p>
            <Segment vertical>
                <Header as={"h4"}  align={"center"}> Code Written By{" "}
                    <a
                        className="Me-link"
                        href="https://github.com/jmclaughlin0/wetsuit_hub"
                        target="_blank"
                        rel="noopener noreferrer"
                    >
                        Joe McLaughlin
                    </a>
                </Header>
                <Header as={"h4"}  align={"center"}> Background Images By{" "}
                <a

                    href="https://www.instagram.com/teganwphotography/"
                    target="_blank"
                    rel="noopener noreferrer"
                >
                    Tegan Ward
                </a
                    >
            </Header>

            </Segment>
        </p>
    )
}