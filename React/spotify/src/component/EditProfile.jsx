import React from 'react';
import "../css/edit-profile.css";
import { useContext } from 'react';
import { DataUserContext } from './Provider/SpotifyContex';
import MyPlayLists from "./MyPlaylists";


const EditProfile = () => {

    const {dataUser} = useContext(DataUserContext)
    
    return (
            <div  >
                <div className="img-container">
                        <img className="edit-profile rounded-circle border" src= {dataUser.image} alt="Avatar de usuario" />
                </div>
                <div className="user-info">
                        <h5>User Display Name: {dataUser.displayNam}</h5>
                        <h6>ID de usuario: {dataUser.id}</h6>
                        <h6># Listas: {dataUser?.myPlaylist.length}</h6>
                </div>
                <MyPlayLists />
            </div>       
    )
}
export default EditProfile;