package org.example.api.mapper

import org.example.model.PlaylistDTO
import org.github.unqui.Playlist

class PlayListMapper {

    fun tranformarADto(playList: Playlist):PlaylistDTO{
        return PlaylistDTO( playList.id,
                            playList.name,
                            playList.description,
                            playList.image,
                            playList.songs,
                            UserMapper().toUserDrafDTO(playList.author),
                            playList.lastModifiedDate.toString(),
                            playList.likes.map { it-> UserMapper().toUserDrafDTO(it) },
                            playList.duration())
    }
}