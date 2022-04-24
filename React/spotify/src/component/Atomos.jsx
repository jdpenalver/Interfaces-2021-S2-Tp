
import { Link } from "react-router-dom";

const InputTextLogin = ({seccion, setFuncion}) => {
    const handler = (e) => {
      setFuncion(e.target.value)
    } 
    return(
      <div className="mb-3">
      <label htmlFor={seccion} className="form-label">{seccion}</label>
      <input type={seccion==='Password'?'password':'text'} className="form-control colorsito" id={seccion} aria-describedby="descripcion" onChange={handler} />
      <div id="descripcion" className="form-text">Introduce tu {seccion}.</div>
      </div>
    );
  }
  
  const ImagenSpotify = () => {
    return(
      <img src='https://i.ibb.co/4pZW8Y7/spotify.png' className='logosp' alt="Logo de spotify"></img>
    );
  }
  
  const Boton = ({funciondeboton, loguear}) => {
   
    return(
      <button type="button" className="btn btn-success color-spotify" onClick={loguear}>{funciondeboton}</button>
    );
  }
  
  const FooterLogin = () => {
    return(
      <div className ="text-white footerlogin">¿NO TENÉS CUENTA? <Link to="/register">REGISTRATE</Link></div>
    );
  }
  
  const FooterCreateAccount = () => {
    return(
      <div className ="text-white footerlogin">¿YA TENÉS CUENTA? <Link to="/">LOGUEATE</Link></div>
    );
  }

  export {InputTextLogin, ImagenSpotify, Boton, FooterLogin, FooterCreateAccount}