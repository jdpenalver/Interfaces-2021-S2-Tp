import React, { useState } from "react";
import Api from "../api/SpotifyApi";
import { SpotifyContext } from "./Provider/SpotifyContex";
import { useContext } from 'react';

import "../css/barra-busqueda.css"


const BarraBusqueda = () => {
    const {spotifyContext} = useContext(SpotifyContext)
    const [parametroBusqueda, setParametroBusqueda] = useState("")

    const handlerQuery = (e) => {
        setParametroBusqueda(e.target.value)
    }
    const definirBusqueda = (texto) => {
        spotifyContext.setSearchString(texto)
        spotifyContext.setAmostrar("BUSQUEDA")
    }

    const getSearch = () => {
        Api.getSearch(parametroBusqueda).then((d) => definirBusqueda(d))
        }

    return (
        <div className="contenedor-barra-busqueda w-50">
            <div className="barra-busqueda input-group">
                <input type="text" onChange={handlerQuery} className="form-control" placeholder="Temas, Listas o Usuarios" aria-label="Temas, Listas o Usuarios" aria-describedby="button-addon2"></input>
                <button onClick={getSearch} className="btn btn-outline-secondary" type="button" id="button-addon2">Buscar</button>
            </div>
        </div>
    );
}


export default BarraBusqueda;