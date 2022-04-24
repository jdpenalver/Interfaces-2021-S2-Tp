import "../css/reproductor.css"
import { Outlet } from "react-router";
import { Player } from "react-yt-sound-player";
import { useContext } from 'react';
import { SpotifyContext } from './Provider/SpotifyContex';

const Reproductor = () => {
    
    const {spotifyContext} = useContext(SpotifyContext)

    return(
        <div className='reproductor'>
            <Player songs={spotifyContext.listaSong}/>
            <Outlet />
        </div>
    );
}
export default Reproductor