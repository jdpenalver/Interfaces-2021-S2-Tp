import "../css/createAccount.css";
import { useState } from "react";
import { ImagenSpotify,InputTextLogin, FooterCreateAccount } from "./Atomos";
import Api from "../api/SpotifyApi";
import { useNavigate } from "react-router";
import { useEffect } from "react";
import { SpotifyContext } from './Provider/SpotifyContex';
import { useContext } from "react";

const FormCreateAccount = () => {
    const [username, setUserName] = useState("")
    const [password, setPassword] = useState("")
    const [email, setEmail] = useState("")
    const [error, setError] = useState("")

    const [token, setToken] = useState('')
    
    const navigate = useNavigate()

    const {spotifyContext} = useContext(SpotifyContext)

    let usuario = {
        'name': username,
        'email': email, 
        'password':password,
        'image': 'https://robohash.org/' + Math.random()*15  // img aleatoria
    }

    useEffect(() => {
        if (token !== '') {
          localStorage.setItem('token', token)
          spotifyContext.setAmostrar("USUARIO")
          navigate("/logged",  {state: { newToken:token }} )}
      }, [token])

    

    const validar = () => {
        let valida = true
        if(username.length < 4){
            setError("El usuario debe ser mayor a 4 caracteres")
            valida = false
        }
        if(password.length < 4){
            setError("La contraseña debe tener más de 4 caracteres")
            valida = false
        }
        if(!email.includes("@") || !email.includes(".")){
            setError("Formato de mail inválido")
            valida = false
        }
        return valida
    }

    const registracion = (e) => {
        e.preventDefault()
        if (validar()){
            setError('') 
            Api.registrarUsuario(JSON.stringify(usuario), setToken)
        }
    }
    
    return(
    <div className='w-auto vh-100 login'>
        <div className='blur d-flex w-auto vh-100'>
            <form className='formulario w-25 m-auto bg-dark text-white d-flex flex-column rounded' onSubmit={registracion}>
                <ImagenSpotify/>
                <InputTextLogin seccion ={'Username'} setFuncion={setUserName} />
                <InputTextLogin seccion ={'Password'} setFuncion ={setPassword}/>
                <InputTextLogin seccion ={'Email'} setFuncion={setEmail}/>
                <button type="submit" className="btn btn-success color-spotify">CREAR CUENTA</button>
                <div className="etiquetaRoja">{error}</div>
                <FooterCreateAccount/>
            </form>
        </div>
    </div>
    );
  }


  export default FormCreateAccount;