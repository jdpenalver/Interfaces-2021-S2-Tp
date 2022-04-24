package org.example.api.mapper

import org.example.model.PlaylistDTO
import org.example.model.UserDraftDTO
import org.github.unqui.Playlist
import org.github.unqui.Song
import org.github.unqui.User

class SearchMapper ( )
{
    var users= mutableListOf<UserDraftDTO>()
    var playlist = mutableListOf<PlaylistDTO>()
    var songs= mutableListOf<Song>()

    fun addUser(usersToAdd:List<User>){
        usersToAdd.forEach { aUser->users.add(UserMapper().toUserDrafDTO(aUser)) }
    }

    fun addSong (songToAdd:List<Song>){
        songs = songToAdd.toMutableList()
    }

    fun addPlayList(playListToAdd:List<Playlist>){
        playListToAdd.forEach { aPlayList->playlist.add(PlayListMapper().tranformarADto(aPlayList)) }
    }

}
