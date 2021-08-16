import './App.css';
import {Icon} from "semantic-ui-react";

function Home() {
    return (
        <div className="App">
            <header className="App-header">
                <Icon name={"universal access"} size={"massive"} className="App-logo" alt="logo" />
                <p>
                    Welcome to Wetsuit Hub!!
                    <br/>
                    Navigate to the Wetsuits Page to find great deals on Wetsuits
                </p>
            </header>
            <p>
                Built by Surfers for Surfers
            </p>
        </div>
    );
}

export default Home;