import React from 'react'
import { SpotifyContext } from './Provider/SpotifyContex';
import { useContext } from 'react';
import "../css/cards.css"

 const CardPlayList = ({misLista}) => {
    
    const enMinutosYSegundos = (duracion) => {
        return (Math.floor(duracion / 60) + "m " + duracion % 60 + "s");
    }

    const {spotifyContext} = useContext(SpotifyContext)
    
    const mostrarPlaylist = () => {
        spotifyContext.setAmostrar("LISTA")
        spotifyContext.setPlayList(misLista)
    }
    
    return (
        <div className="cardPlayList">
            <img className="imagenCardLista" src={misLista.image} />
            <div className="card-body">
                <h5 className="card-title">Description:{misLista.description}</h5>
                <p className="card-text">Duration: { enMinutosYSegundos(misLista.duration)}</p>
                <button className=" btn btn-primary"onClick={mostrarPlaylist} > Go PlayList</button>
            </div>
        </div>
    )
}
export default CardPlayList
