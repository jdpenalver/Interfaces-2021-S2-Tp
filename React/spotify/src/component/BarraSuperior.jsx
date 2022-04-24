import BarraBusqueda from './BarraBusqueda';
import PerfilUsuarioBar from './PerfilUsuarioBar';
import "../css/barra-superior.css";

const BarraSuperior = () => {
    return (
        <div className="barra-superior mostrar">
            <BarraBusqueda  />
            <PerfilUsuarioBar />
        </div>
    )
}


export default BarraSuperior
