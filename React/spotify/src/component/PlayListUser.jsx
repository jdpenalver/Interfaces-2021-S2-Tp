import React from 'react';
import "../css/playlist-user.css";
import CardPlayList from './CardPlayList';
import { useContext } from 'react';
import { DataUserContext } from './Provider/SpotifyContex';

const PlayListUser = () => {
    
const {dataUser} = useContext(DataUserContext)

    return (
        <div className="card-group">
        { dataUser.myPlaylist.map( unaLista => <CardPlayList misLista={unaLista} />)  } 
        </div>
    )
}

export default PlayListUser
