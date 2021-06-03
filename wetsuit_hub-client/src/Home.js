import logo from './logo.svg';
import './App.css';
import {wetsuitsPage} from "./URLS";

function Home() {
    return (
        <div className="App">
            <header className="App-header">
                <img src={logo} className="App-logo" alt="logo" />
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