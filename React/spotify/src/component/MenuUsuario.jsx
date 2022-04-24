import React from 'react'
import "../css/menu-usuario.css"
import {  useNavigate } from "react-router";
import { Link } from 'react-router-dom';
import { useContext } from 'react';
import { SpotifyContext } from './Provider/SpotifyContex';
import { DataUserContext } from './Provider/SpotifyContex';
const MenuUsuario = ({mostrar}) => {

const {spotifyContext} = useContext(SpotifyContext)
const {dataUser} = useContext(DataUserContext)

  const navigate= useNavigate()
  const cerrarSesion = () => {
    localStorage.removeItem('token')
    dataUser.setDisplayNam("")
    dataUser.setImage ("")
    dataUser.setMyPlaylist([])
    dataUser.setPlayList("")
    dataUser.setLikes([])
    dataUser.setId("")
    navigate('/')
  }
const irAPerfil = () => {
  spotifyContext.setAmostrar("USUARIO");
}
    return (
      <div className={`${mostrar? 'hide': 'show'}`}>
        <ul className="menu-usuario d-flex justify-content-end flex-column">
            <li onClick={() => {irAPerfil()} }><Link className="dropdown-item menu-item algo" to=""> Ver perfil</Link>  </li>
            <li onClick={cerrarSesion}><a className="dropdown-item menu-item algo">Cerrar sesion</a></li>
          </ul>
      </div>
      
      )
}

export default MenuUsuario
