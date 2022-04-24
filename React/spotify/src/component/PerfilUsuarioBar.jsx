import React, { useState } from 'react';
import MenuUsuario from './MenuUsuario'
import "../css/perfil-usuario-bar.css";
import { useContext } from 'react';
import { DataUserContext } from './Provider/SpotifyContex';

const PerfilUsuarioBar = () => {

const {dataUser} = useContext(DataUserContext)

let [menuVisible, setmenuVisible] = useState(true)

const mostrarMenu =  () =>setmenuVisible = setmenuVisible(!menuVisible)

    return (
        <div className="perfil-usuario-bar rounded-pill">
            
            <div className="avatar-container rounded-circle">
                <img className="avatar" src={dataUser.image} alt="" />  
            </div>
            
            <label className=" fw-bold text-light">{dataUser.displayNam} </label>
            
            <div onClick={mostrarMenu} className="me-2 down-icon boton"></div>
         <MenuUsuario mostrar={menuVisible}></MenuUsuario>
    </div>
    )
}

export default PerfilUsuarioBar
