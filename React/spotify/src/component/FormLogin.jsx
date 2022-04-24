import { ImagenSpotify,InputTextLogin, Boton, FooterLogin } from "./Atomos";
import '../css/formlogin.css'
import { useEffect, useState } from "react";
import Api from "../api/SpotifyApi";
import {useNavigate} from 'react-router'


const FormLogin = () => {

    const [user, setuserData] = useState('')
    const [pass, setpass] = useState('')
    const [token, setToken] = useState('')
    const [error, setError] = useState("")

  useEffect(() => {
    if (token !== '') {
      localStorage.setItem('token', token)
      navigate("/logged",  {state: { newToken:token }} )}
    
  }, [token])

  const navigate = useNavigate()
    let userdata = {
      email: user,
      password: pass
    }


    const validar = () => {
      let valida = true
      if(pass.length < 1){
          setError("Ingrese contraseña")
          valida = false
      }
      if(!user.includes("@") || !user.includes(".")){
          setError("Formato de mail inválido")
          valida = false
      }
      return valida
  }
    
  const loguear =   () => {
    if (validar()) {
    
      Api.getToken(JSON.stringify(userdata), setToken,setError)


    }
    
  }  
    return (
    <div className='w-auto vh-100 login'>
      <div className='blur d-flex w-auto vh-100'>
          <form className='formulario w-25 m-auto bg-dark text-white d-flex flex-column rounded'>
            <ImagenSpotify/>
            <InputTextLogin seccion ={'Email'} setFuncion= {setuserData} />
            <InputTextLogin seccion ={'Password'} setFuncion= {setpass}/>
            <Boton funciondeboton ={'INICIAR SESIÓN'} loguear={loguear}/>
            <div className="etiquetaRoja">{error}</div>
            <FooterLogin/>
          </form>
      </div>
    </div>
    );
  }

  export default FormLogin;