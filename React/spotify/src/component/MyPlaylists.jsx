import '../css/playlist-user.css';
import { useContext } from 'react';
import { SpotifyContext } from './Provider/SpotifyContex';
import { DataUserContext } from './Provider/SpotifyContex';

const MyPlayLists = () => {

    const {spotifyContext} = useContext(SpotifyContext)
    const {dataUser} = useContext(DataUserContext)

    
    const enMinutosYSegundos = (duracion) => {
        return (Math.floor(duracion / 60) + "m " + duracion % 60 + "s");
    }
    
    const mostrarPlaylist = (indice) => {
            spotifyContext.setAmostrar("LISTA")
            spotifyContext.setPlayList(dataUser.myPlaylist[indice])
    }

    const playsongs = (indice) => {
        spotifyContext.setListaSong(dataUser.myPlaylist[indice].songs.map(song => new URL(song.url).searchParams.get("v")))
    }
    
    return (
        <div>
            <h5>Tus Playlists:
                <p><small className="text">Selecciona una para ver detalle</small></p>
            </h5>
            <table className="table table-hover table-dark">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Image</th>
                        <th scope="col">Name</th>
                        <th scope="col">Description</th>
                        <th scope="col">Duration</th>
                        <th scope="col"># Songs</th>
                        <th scope="col">Likes 👍</th>
                        <th scope="col">Play</th>
                    </tr>
                </thead>
                <tbody>
                    { dataUser.myPlaylist? ( dataUser.myPlaylist.map ((list, i) =>
                    <tr key={Math.random() *1000}>
                        <th onClick={() => mostrarPlaylist(i)} scope="row">{i + 1}</th>
                        <td onClick={() => mostrarPlaylist(i)}><img src={list.image} width="50px" height="50px" alt="Imagen" /></td>
                        <td onClick={() => mostrarPlaylist(i)}>{list.name}</td>
                        <td onClick={() => mostrarPlaylist(i)}>{list.description}</td>
                        <td onClick={() => mostrarPlaylist(i)}>{ enMinutosYSegundos(list.duration) }</td>
                        <td onClick={() => mostrarPlaylist(i)}>{list.songs.length}</td>
                        <td onClick={() => mostrarPlaylist(i)}>{list.likes.length}</td>
                        <td className="pointer" onClick={() => playsongs(i)}>▶️</td>
                    </tr>)) : 
                    (<tr>
                        <th scope="row">--</th>
                        <td>--</td>
                        <td>--</td>
                        <td>--</td>
                        <td>--</td>
                        <td>--</td>
                        <td>--</td>
                        <td>--</td>
                    </tr>)
                    }
                </tbody>
            </table>
        </div>
    );
}

export default MyPlayLists;