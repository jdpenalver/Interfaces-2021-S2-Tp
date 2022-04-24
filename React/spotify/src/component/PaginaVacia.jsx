import { Link } from "react-router-dom"
import "../css/paginaVacia.css"


const PaginaVacia = () => {


return (
    <div className="paginaVacia">
        
    <div className="texto">
        <h3>
            Esta página ya no existe.
        </h3>
        <p>
            No pudimos encontrar la página que estabas buscando, te ayudamos a volver a casa
        </p>
      <Link to="/"> ir al Home </Link>
    </div>
    <div className="imagen">
        <div className="disco"></div>
    </div>

</div>


)

}

export default PaginaVacia