
    import { useContext } from 'react';
    import { ExternalUserContext} from './Provider/SpotifyContex';
    
const UserProfile = () => {
    
    const {externalUser} = useContext(ExternalUserContext)
    

    return(
        <div>
        Nombre: {externalUser.displayNam}
        <img src={externalUser.image} alt="Imagen"></img>
        </div>
        
    )

}

export default UserProfile