import { BrowserRouter, Routes, Route } from 'react-router-dom';
import FormCreateAccount from './component/FormCreateAccount';
import FormLogin from './component/FormLogin';
import ContenedorPrincipal from './component/ContenedorPrincipal';
import EditProfile from './component/EditProfile';
import PaginaVacia from './component/PaginaVacia';
import "../src/css/colors.css"
import ListaSeleccionada from './component/listaSeleccionada';
import MyPlayLists from './component/MyPlaylists';
import { SpotifyProvider } from './component/Provider/SpotifyContex';
import { ExternalUseProvider } from './component/Provider/SpotifyContex';
import { DataUserProvider } from './component/Provider/SpotifyContex';

const App = () => {
  document.title = "UNQ UIs - G7-Spoti";
  return (
  <>
  
  <BrowserRouter>
  <SpotifyProvider>
  <DataUserProvider>
  <ExternalUseProvider>
    <Routes>
      <Route path="/" element={<FormLogin/>}/>
      <Route path="/register" element={<FormCreateAccount/>}/>
      <Route path="/logged" element={<ContenedorPrincipal/>} >
        <Route path="/logged/playList/:listaId" element = {<ListaSeleccionada />} />
        <Route path="/logged/myPlayLists/" element = {<MyPlayLists />} />
        <Route path="/logged/profile" element = {<EditProfile />} />
      </Route>
      <Route path="*" element={<PaginaVacia/>}/>
    </Routes>
    </ExternalUseProvider>
    </DataUserProvider>
    </SpotifyProvider>
  </BrowserRouter>
  
     
  </>
  );
}

export default App;







