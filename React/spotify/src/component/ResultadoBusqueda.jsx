import CardPlayList from "./CardPlayList";
import CardSong from "./CardSong";
import CardUser from "./CardUser";
import { useContext } from 'react';
import { SpotifyContext } from './Provider/SpotifyContex';
import "../css/cards.css";


const ResultadoBusqueda = () => {

    const {spotifyContext} = useContext(SpotifyContext)

    return(
        <>
        <div className="contenedorResultado">
        <h1>Usuarios</h1>
        {spotifyContext.searchString.users.length > 0? (<div className="cardContenedorUsuario">{spotifyContext.searchString.users.map(a => <CardUser usuario={a} ></CardUser>)}</div>) : (<div>No se encontrar usuarios</div>) }
        <h1>Playlist</h1>
        {spotifyContext.searchString.playlist.length > 0?  (<div className="cardContenedorPlaylist">{spotifyContext.searchString.playlist.map(a => <CardPlayList misLista={a} ></CardPlayList>)}</div>) : (<div>No se encontrar playlists</div>)}
        <h1>Canciones</h1>
        {spotifyContext.searchString.songs.length > 0?  (<div className="cardContenedorSongs">{spotifyContext.searchString.songs.map(a => <CardSong song={a}></CardSong>)}</div>): (<div>No se encontrar canciones</div>)}
        </div>
        </>
    );
}

export default ResultadoBusqueda;