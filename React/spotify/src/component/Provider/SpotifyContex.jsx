import React, { createContext } from 'react'

import { useState } from 'react';

export const SpotifyContext = createContext()
export const DataUserContext = createContext()
export const ExternalUserContext = createContext()

export const ExternalUseProvider =({children}) => {

    const [playList, setPlayList] = useState("")
    const [image, setImage] = useState("")
    const [displayNam, setDisplayNam] = useState("")
    const [id, setId] = useState("")
    const [myPlaylist,setMyPlaylist] = useState([])
    const [usuarioExterno, setUsuarioExterno] = useState("")
 
    const externalUser= {
        playList, 
        image, 
        displayNam,
        id, 
        myPlaylist, 
        usuarioExterno, 
        setUsuarioExterno,
        setMyPlaylist,
        setId,
        setDisplayNam, 
        setImage,
        setPlayList
    }
    return (
        <ExternalUserContext.Provider value = {{externalUser}}>
            {children}
        </ExternalUserContext.Provider>
    )

}


export const DataUserProvider =({children}) => {

    const [displayNam, setDisplayNam] = useState("")
    const [id, setId] = useState("")
    const [image,setImage] = useState("")
    const [likes,setLikes] = useState([])
    const [myPlaylist,setMyPlaylist] = useState([])
    const [playList, setPlayList] = useState("")
    const [img, setImg] = useState("")

    const dataUser= {
        playList, 
        img, 
        displayNam,
        id, 
        myPlaylist,
        likes,
        image,
        setImage, 
        setLikes, 
        setMyPlaylist,
        setId,
        setDisplayNam, 
        setImg,
        setPlayList
    }
    return (
        <DataUserContext.Provider value = {{dataUser}}>
            {children}
        </DataUserContext.Provider>
    )

}



export const SpotifyProvider = ({children}) => {
  
    const [aMostrar, setAmostrar] = useState("USUARIO")
    const [playList, setPlayList] = useState("")
    const [searchString, setSearchString] = useState("")
    const [usuarioExterno, setUsuarioExterno] = useState("")
    const [listaSong, setListaSong] = useState([])
    const [usuario, setUsuario] = useState([])
  
     const spotifyContext = {
                aMostrar, 
                playList,
                searchString, 
                usuarioExterno, 
                listaSong, 
                usuario, 
                setAmostrar,
                setPlayList, 
                setSearchString, 
                setUsuarioExterno, 
                setListaSong, 
                setUsuario
        }      

      return (
          <SpotifyContext.Provider value = {{spotifyContext}}>
              {children}
          </SpotifyContext.Provider>
      )
  }
