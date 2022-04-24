package viewmodel

import SpotifyService
import org.github.unqui.*
import org.uqbar.commons.model.annotations.Observable


@Observable
class UserViewModel(var usuario : User, var service: SpotifyService) {
    var username = usuario.displayName
    var email = usuario.email
    var id = usuario.id
    var image = usuario.image
    var password = usuario.password
    var lists :MutableList<PlaylistViewModel> = transformar(usuario.myPlaylists)
    var likes = usuario.likes
    var idListSeleccionada: PlaylistViewModel? = null


    fun transformar(lists: MutableList<Playlist>): MutableList<PlaylistViewModel>{
        var listaNueva = mutableListOf<PlaylistViewModel>()

        lists.forEach {it -> listaNueva.add( PlaylistViewModel( it.id,service) ) }
        return  listaNueva
    }

    fun updatePlayList(playlistViewModel: PlaylistViewModel){

      var listDraf =
          PlayListDraft(playlistViewModel.name,
              playlistViewModel.description,
              playlistViewModel.image,
              toSong(playlistViewModel.songsDraft))

        lists.removeIf { it-> it.id == playlistViewModel.id }
        service.modifyPlaylist(id, playlistViewModel.id, listDraf)

        lists.add(playlistViewModel)


    }

    fun toSong (list: MutableList<SongViewModel>): MutableList<Song>{
            var listaNueva = mutableListOf<Song>()
            list.forEach {it -> listaNueva.add( Song( it.id,it.name,it.band,it.url,it.duration) ) }
            return  listaNueva
    }

    fun updateAll(){
        usuario = service.getUser(usuario.id)
        username = usuario.displayName
        email = usuario.email
        id = usuario.id
        image = usuario.image
        password = usuario.password
        lists = transformar(usuario.myPlaylists)
        likes = usuario.likes
        idListSeleccionada = null
    }





}