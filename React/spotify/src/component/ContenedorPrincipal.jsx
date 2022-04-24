import BarraLateral from "./barraLateral";
import Reproductor from "./Reproductor";
import Principal from "./Principal";
import Api from "../api/SpotifyApi";
import "../css/contenedorPrincipal.css"
import {  useNavigate } from "react-router";
import { useContext } from 'react';
import { DataUserContext } from './Provider/SpotifyContex';
import { useState } from "react";
import { useLocation } from "react-router-dom";
import { useEffect } from "react";

const ContenedorPrincipal = () => {
    const {dataUser} = useContext(DataUserContext)
    const [loading, setLoading] = useState(true)
    const navigate = useNavigate()
    const location= useLocation()
    useEffect(() => {
        if (loading) {
            
            if(Object.keys(location.state).includes('newToken')) {
                Api.userLoginConToken(location.state.newToken, cargarInfor)
            }else{
                navigate("/")
            }    
        }
    
    },[loading])

const cargarInfor = (data)=> {
    dataUser.setDisplayNam(data.displayNam)
    dataUser.setImage (data.image)
    dataUser.setMyPlaylist(data.myPlaylist)
    dataUser.setLikes(data.likes)
    dataUser.setId(data.id)
    setLoading(false)
}

    return (
        <div className='contenedor-principal'>
            <BarraLateral   />
            <Principal   /> 
            <Reproductor />
            {loading? <h1>Cargaaanndoo</h1> : ''}
        </div>
    );
  
}

export default ContenedorPrincipal;