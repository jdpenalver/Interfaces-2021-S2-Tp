import Axios  from "axios";
                      

const server = "http://localhost:7000/"

const tokenLocal = localStorage.getItem('token');

                            
const getConHeaders = (url,encabezados) => Axios.get (url, 
                                         { headers: { Authorization:encabezados} })
                                            .then((data) => data)
                                            .catch((e)=>e)



const getConLocalToken = (path) => getConHeaders(server + path ,tokenLocal )

const dataCurrentUser = () => getConLocalToken("user") 
                                      
const postConToken = (url, body) => Axios.post(server + url , body ,  { headers: { Authorization: localStorage.getItem('token')} })
                    .then((data) => data)
                    .catch((error)=> Promise.reject(error.response.data))

const get = (url) => Axios.get (url,  { headers: { Authorization: localStorage.getItem('token')} })
                    .then((data) => data)
                    .catch((error)=> Promise.reject(error.response.data))

const getConToken = (path)=>get( server + path, {headers: { Authorization: localStorage.getItem('token')}  })
                                    .then(({data})=> data)
                                    .catch((e)=> e) 

const getListaPorId = (listId)=>Axios.get(server + "playlist/" + listId, {headers: { Authorization: localStorage.getItem('token')} })
                    .then(({data})=> data)
                    .catch((e)=> e) 

const getSearch = (parametro) => getConToken("search?q=" + parametro)


const getToken = (userData,setterToken,setError) => {
        
    Axios.post(server + "login", userData)
     .then(response => setterToken(response.headers.authorization ))
     .catch((e)=>setError("usuario o contraseña invalido"))
     
    }


const getUserData = (token, setData) => {
    Axios.get (server + 'user', 
        { headers: { Authorization:token} })
        .then(({data}) => setData(data))
        .catch((e)=>e)
}

const registrarUsuario = (data, setterToken ) => {
    Axios.post (server + 'register',data)
    .then(response => setterToken(response.headers.authorization) )
}

const userLoginConToken = (userToken, setData) => {
    Axios.get(server + 'user', { headers: { Authorization:userToken} })
    .then(({data}) => { setData(data) })
}
  

                        
const Api = { 
    userLoginConToken,
    registrarUsuario,
    getUserData,
    getSearch,
    getConToken,
    get, 
    postConToken, 
    getListaPorId,
    dataCurrentUser,
    getToken
}

 export default Api