import '../css/principal.css';
import Cuerpo from './Cuerpo';

import BarraSuperior from './BarraSuperior';

const Principal = () => {
    
    return (
        <div className="principal">
            <BarraSuperior  />
            <Cuerpo />
        </div>
    )
}
export  default Principal