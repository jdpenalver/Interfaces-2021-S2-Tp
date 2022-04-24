import React from 'react'
import { useContext } from 'react';
import { SpotifyContext } from './Provider/SpotifyContex';

 const CardSong = ({song}) => {
     
    const {spotifyContext} = useContext(SpotifyContext)

    const enMinutosYSegundos = (duracion) => {
        return (Math.floor(duracion / 60) + "m " + duracion % 60 + "s");
    }
    const reproducir =() =>{
        spotifyContext.setListaSong([new URL(song.url).searchParams.get("v")])
        
    }
    return (
        <div className="cardSong">
            <div className="card-body">
                <h5 className="card-title">{song.name}</h5>
                <p className="card-text"> {song.band}</p>
                <p className="card-text">duracion: {enMinutosYSegundos(song.duration)}</p> 
                <button className=" btn btn-primary" onClick={reproducir}> Play</button>
               
            </div>
        </div>
    )
}
export default CardSong