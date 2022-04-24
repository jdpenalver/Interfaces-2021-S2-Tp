package viewmodel

import SpotifyService
import errors.PlayListError
import org.github.unqui.*
import org.uqbar.commons.model.annotations.Observable
import java.time.LocalDateTime

@Observable
class PlaylistViewModel(var id : String, service: SpotifyService) {
    var name: String
    var description: String
    var image: String
    var songs: List<SongViewModel>
    val author: User
    var lastModifiedDate: LocalDateTime
    val likes: MutableList<User>
    var duration: Int
    var ammountOfSongs: Int
    var duracion: String
    var songsDraft: MutableList<SongViewModel>
    var songToAdd: SongViewModel? = null
    var songToQuit: SongViewModel? = null

    fun duracionInSec(): String{
        return (duration / 60).toInt().toString() + ":" + (duration % 60).toString()
    }

    init {
        var list = service.getPlaylist(id)
        name = list.name
        description = list.description
        image = list.image
        songs = transformar(list.songs)
        author = list.author
        lastModifiedDate = list.lastModifiedDate
        likes = list.likes
        duration = list.duration()
        ammountOfSongs = list.songs.size
        duracion = duracionInSec()
        songsDraft = songs as MutableList<SongViewModel>
    }
    var todasLascanciones = transformar(service.getAllSongs())
    var todasLasCancionesFiltradas = filtrarListaDeCanciones()

    fun  filtrarListaDeCanciones(): MutableList<SongViewModel>{
        return todasLascanciones.filter { it -> noEstaEn(it) } as MutableList<SongViewModel>
    }
    fun noEstaEn(song: SongViewModel): Boolean {
        return ! songs.any { it -> (it.name == song.name) }
    }

    fun transformar(songs: List<Song>): MutableList<SongViewModel>{
        var listaNueva = mutableListOf<SongViewModel>()
        songs.forEach {it -> listaNueva.add(SongViewModel(it.id, it.name, it.band, it.url, it.duration))}
        return  listaNueva
    }

    fun addSongASongsDraft(){
        songsDraft.add(songToAdd!!)
        todasLasCancionesFiltradas = filtrarListaDeCanciones()
    }

    fun quitSongASongsDraft(){
        songsDraft.remove(songToQuit)
        todasLasCancionesFiltradas = filtrarListaDeCanciones()
    }

    fun validarYAgregar(){
        if (  songToAdd != null) {
            addSongASongsDraft()
            songToAdd = null
        }
    }

    fun validarYQuitar(){
        if ( songToQuit != null) {
            quitSongASongsDraft()
            songToQuit = null
        }
    }

    fun aceptEditPlaylist(usermodel:UserViewModel, service: SpotifyService) {
        if (estaRepetito(service)) throw PlayListError("Playlisy with same name.")
        usermodel.updatePlayList(this )
        usermodel.updateAll()
    }
    fun estaRepetito(service: SpotifyService): Boolean{
        var listaActual = service.getUser(author.id).myPlaylists
        return listaActual.count { it -> it.name == name && it.id != id} >= 1

    }


}