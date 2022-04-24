package org.example.api.mapper

import org.example.model.RegistroDTO
import org.example.model.UserDTO
import org.example.model.UserDraftDTO
import org.github.unqui.User
import org.github.unqui.UserDraft

class UserMapper {
    fun toUserDrafDTO(user:User): UserDraftDTO {
        return  UserDraftDTO( user.id, user.displayName,user.image)
    }

    fun toUserDTO(user: User): UserDTO{
        return UserDTO(user.id,
                       user.displayName,
                       user.image, user.myPlaylists.map { it-> PlayListMapper().tranformarADto(it) },
                       user.likes.map { it-> PlayListMapper().tranformarADto(it)})
    }

    fun toUserDraft(registroDTO : RegistroDTO) : UserDraft {
        return UserDraft(registroDTO.email, registroDTO.image, registroDTO.password, registroDTO.name)
    }
}

