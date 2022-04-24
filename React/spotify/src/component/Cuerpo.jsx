import "../css/cuerpo.css";
import ListaSeleccionada from "./listaSeleccionada";
import MyPlayLists from "./MyPlaylists";
import EditProfile from "./EditProfile";
import ResultadoBusqueda from "./ResultadoBusqueda";
import UserProfile from "./UserProfile";
import { useContext } from 'react';
import { SpotifyContext } from './Provider/SpotifyContex';

const Cuerpo = () =>  {
    
    const {spotifyContext} = useContext(SpotifyContext)

    const queMostrar = () =>{
        switch (spotifyContext.aMostrar){
            case "USUARIO": return <EditProfile />;
            case "LISTAS": return <MyPlayLists />;
            case "LISTA": return <ListaSeleccionada />;
            case "BUSQUEDA": return <ResultadoBusqueda />;
            case "USUARIOEXTERNO": return <UserProfile />
        }
    }

    return (
        <div className="cuerpo">
            {queMostrar()}
        </div>
    )
}


export default Cuerpo