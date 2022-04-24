package viewmodel

import SpotifyService
import org.github.unqui.PlayListDraft
import org.github.unqui.Song
import org.uqbar.commons.model.annotations.Observable


@Observable
class AddPlayListViewModel(var userViewModel: UserViewModel , service : SpotifyService) {
    var name = ""
    var description = ""
    var image = ""
    var songToAdd : SongViewModel? = null
    var songToQuit : SongViewModel? = null
    var songsDraft : MutableList<SongViewModel> = mutableListOf()
    var todasLascanciones = transformar(service.getAllSongs())
    var todasLasCancionesFiltradas = filtrarListaDeCanciones()


    fun  filtrarListaDeCanciones(): MutableList<SongViewModel>{
        return todasLascanciones.filter { it -> noEstaEn(it) } as MutableList<SongViewModel>
    }

    fun noEstaEn(song: SongViewModel): Boolean {
        return ! songsDraft.any { it -> (it.name == song.name) }
    }

    fun transformar(songs: List<Song>): MutableList<SongViewModel>{
        var listaNueva = mutableListOf<SongViewModel>()
        songs.forEach {it -> listaNueva.add(SongViewModel(it.id, it.name, it.band, it.url, it.duration))}
        return  listaNueva
    }

    fun validarYAgregar(){
        if (songToAdd != null) {
            addSongASongsDraft()
            songToAdd = null
        }
    }

    fun validarYQuitar(){
        if (songToQuit != null) {
            quitSongASongsDraft()
            songToQuit = null
        }
    }

    fun addSongASongsDraft(){
        songsDraft.add(songToAdd!!)
        todasLasCancionesFiltradas = filtrarListaDeCanciones()
    }

    fun quitSongASongsDraft(){
        songsDraft.remove(songToQuit)
        todasLasCancionesFiltradas = filtrarListaDeCanciones()
    }

    fun toSong (list: MutableList<SongViewModel>):MutableList<Song>{
        var listaNueva = mutableListOf<Song>()
        list.forEach {it -> listaNueva.add( Song( it.id,it.name,it.band,it.url,it.duration) ) }
        return  listaNueva
    }

    fun acceptAdd(service: SpotifyService) {
        var songs = toSong(songsDraft)
        var playlist = PlayListDraft(name, description, image, songs)

        service.addPlaylist(userViewModel.id, playlist)
        userViewModel.updateAll()

    }

}