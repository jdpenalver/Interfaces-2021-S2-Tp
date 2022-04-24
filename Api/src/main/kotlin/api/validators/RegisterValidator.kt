package org.example.api.validators

import org.example.model.RegistroDTO

class RegisterValidator {
    fun validarNombreNoVacio(registroDTO: RegistroDTO) : Boolean{
        return registroDTO.name.isNotBlank() && registroDTO.name.isNotEmpty()
    }

    fun validarEmail(registroDTO: RegistroDTO) : Boolean{
        return registroDTO.email.contains("@") && registroDTO.email.contains(".")
    }

    fun validarPassword(registroDTO: RegistroDTO) : Boolean{
        return registroDTO.password.isNotEmpty()
    }

    fun validarImagen(registroDTO: RegistroDTO) : Boolean {
        return registroDTO.image.contains(".jpg") && registroDTO.image.length >= 5
    }
}