import '../css/barraLateral.css';
import { Link } from 'react-router-dom';
import { SpotifyContext } from './Provider/SpotifyContex';
import { useContext } from 'react';
import { DataUserContext } from './Provider/SpotifyContex';

const BarraLateral = () => {

    return (
        <div className="contenedor-barra-lateral">
            <BarraLateralMenu />
            <BarraLateralLista />
        </div>
    );
}

const BarraLateralMenu = () => {

    const {spotifyContext} = useContext(SpotifyContext)

    const mostrarMyPlaylist = () => spotifyContext.setAmostrar("LISTAS")
    const irAInicio = () => spotifyContext.setAmostrar("USUARIO")


    return (
        <div className="barra-lateral-menu menugrid">
            <ul className="barra-menu"> 
                <li onClick={() => irAInicio()}>
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" className="icon-barra-lateral" viewBox="0 0 16 16">
                    <path fill-rule="evenodd" d="M2 13.5V7h1v6.5a.5.5 0 0 0 .5.5h9a.5.5 0 0 0 .5-.5V7h1v6.5a1.5 1.5 0 0 1-1.5 1.5h-9A1.5 1.5 0 0 1 2 13.5zm11-11V6l-2-2V2.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5z"/>
                    <path fill-rule="evenodd" d="M7.293 1.5a1 1 0 0 1 1.414 0l6.647 6.646a.5.5 0 0 1-.708.708L8 2.207 1.354 8.854a.5.5 0 1 1-.708-.708L7.293 1.5z"/>
                    </svg><Link to = "">Inicio</Link>
                </li> 
                <li onClick={() => mostrarMyPlaylist()}>
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" className="icon-barra-lateral" viewBox="0 0 16 16">
                    <path d="M12 13c0 1.105-1.12 2-2.5 2S7 14.105 7 13s1.12-2 2.5-2 2.5.895 2.5 2z"/>
                    <path fill-rule="evenodd" d="M12 3v10h-1V3h1z"/>
                    <path d="M11 2.82a1 1 0 0 1 .804-.98l3-.6A1 1 0 0 1 16 2.22V4l-5 1V2.82z"/>
                    <path fill-rule="evenodd" d="M0 11.5a.5.5 0 0 1 .5-.5H4a.5.5 0 0 1 0 1H.5a.5.5 0 0 1-.5-.5zm0-4A.5.5 0 0 1 .5 7H8a.5.5 0 0 1 0 1H.5a.5.5 0 0 1-.5-.5zm0-4A.5.5 0 0 1 .5 3H8a.5.5 0 0 1 0 1H.5a.5.5 0 0 1-.5-.5z"/>
                    </svg><Link to = "">Tus listas</Link>
                </li>
            </ul>
            <hr></hr>
        </div>
    );
}

const BarraLateralLista = () => {  
    const {dataUser} = useContext(DataUserContext)
    
    const {spotifyContext} = useContext(SpotifyContext) 

    const mostrarPlaylist = (indice) => {
        spotifyContext.setAmostrar("LISTA") 
        spotifyContext.setPlayList(dataUser.myPlaylist[indice])
    }

    return (
            <div className="barra-lateral-listas">
                <ul className="barra-listas"> 
                    {dataUser.myPlaylist ?
                    (dataUser.myPlaylist.map((lista, i) => <li  key={Math.random() *1000} onClick={() => mostrarPlaylist(i)}> <Link to = "">{lista.name}</Link></li> ))
                    : ("NO hay listas")
                    }
                </ul>
            </div>
    );
}

export default BarraLateral