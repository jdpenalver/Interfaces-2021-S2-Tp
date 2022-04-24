import { useContext } from 'react';

import { SpotifyContext } from './Provider/SpotifyContex';

const PlayListSeleccionada = () => {

    const {spotifyContext} = useContext(SpotifyContext)

    const enMinutosYSegundos = (duracion) => {
        return (Math.floor(duracion / 60) + "m " + duracion % 60 + "s");
    }

    const playSong = (url) => {
        spotifyContext.setListaSong([new URL(url).searchParams.get("v")])
    }

    return (
        <div>
            { spotifyContext.playList? (<img src={spotifyContext.playList.image} width="15%" height="15%" className="img-thumbnail" alt="Imagen" />) : ("")}
            <h5>List name:
                <small className="text">{spotifyContext.playList? (spotifyContext.playList.name):('N/A')}</small>
            </h5>
            <h5>Description:
                <small className="text">{spotifyContext.playList? (spotifyContext.playList.description):('N/A')}</small>
            </h5>
            <h5>Duration:
                <small className="text">{spotifyContext.playList? (  enMinutosYSegundos(spotifyContext.playList.duration)  ):('N/A')}</small>
            </h5>
            <h5># Songs:
                <small className="text">{spotifyContext.playList? (spotifyContext.playList.songs.length):('N/A')}</small>
            </h5>
            <h5># Likes 👍:
                <small className="text">{spotifyContext.playList? (spotifyContext.playList.likes.length):('N/A')}</small>
            </h5>
            
            <table className="table table-hover table-dark">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Name</th>
                        <th scope="col">Band</th>
                        <th scope="col">Duration</th>
                        <th scope="col">Play</th>
                    </tr>
                </thead>
                <tbody>
                    { spotifyContext.playList?.songs? ( spotifyContext.playList.songs.map ((song, i) =>
                    <tr key={Math.random() *1000}>
                        <th scope="row">{i + 1}</th>
                        <td>{song.name}</td>
                        <td>{song.band}</td>
                        <td>{ enMinutosYSegundos(song.duration)} s</td>
                        <td className="pointer" onClick={() => playSong(song.url)}>▶️</td>
                    </tr>)) : 
                    (<tr>
                        <th scope="row">--</th>
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

export default PlayListSeleccionada