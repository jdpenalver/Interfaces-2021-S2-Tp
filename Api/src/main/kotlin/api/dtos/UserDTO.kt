package org.example.model

class UserDTO(var id: String,
              var displayNam: String,
              var image: String,
              var myPlaylist: List<PlaylistDTO>,
              var likes: List<PlaylistDTO>) {
}