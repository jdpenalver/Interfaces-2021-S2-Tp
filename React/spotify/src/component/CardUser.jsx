import React from 'react';
import "../css/cards.css"

import { useContext } from 'react';
import { SpotifyContext } from './Provider/SpotifyContex';
import { ExternalUserContext} from './Provider/SpotifyContex';

 const CardUser = ({usuario}) => {
     
     const {externalUser} = useContext(ExternalUserContext)
     const {spotifyContext} = useContext(SpotifyContext)
     
    const mostrarUsuario = () =>{
        spotifyContext.setAmostrar("USUARIOEXTERNO")
        externalUser.setDisplayNam(usuario.displayNam)
        externalUser.setImage(usuario.image)
    }

    return (
        <div className="cardUser">
            <img src={usuario.image} />
            <div className="card-body">
                <h5 className="card-title">{usuario.displayNam}</h5>
                <button className=" btn btn-primary" onClick={mostrarUsuario}> Go profile</button>
            </div>
        </div>
    )
}
export default CardUser