package org.example.model

import org.github.unqui.Song

class PlaylistDTO(val id :String,
                  val name: String,
                  val description: String,
                  val image: String,
                  val songs: List<Song>,
                  val author: UserDraftDTO,
                  val lastModifiedDate: String,
                  val likes: List<UserDraftDTO>,
                  val duration: Int) {
}