package org.example.api.controler

import SpotifyService
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import io.javalin.http.Context
import org.example.api.TokenJWT
import org.example.model.RegistroDTO
import org.example.api.mapper.UserMapper
import org.example.api.validators.RegisterValidator
import org.example.model.Error
import org.github.unqui.UserException
import io.javalin.core.validation.ValidationException
import io.javalin.http.BadRequestResponse


class RegisterController (val spotifyService: SpotifyService, val tokenJWT: TokenJWT) {

    fun register(ctx: Context) {
        try {
            val registerValidator = RegisterValidator()
            val registroDTO = ctx.bodyValidator<RegistroDTO>()
                .check(registerValidator::validarNombreNoVacio,"El nombre no puede ser vacio")
                .check(registerValidator::validarEmail , "El e-mail debe contener @ y punto")
                .check(registerValidator::validarPassword, "La contraseña debe tener al menos 1 caracter")
                .get()
            val user = spotifyService.register(UserMapper().toUserDraft(registroDTO))
            ctx.header("Authorization", tokenJWT.generateToken(user))
            ctx.status(201)
            ctx.json( UserMapper().toUserDTO(user))
        }
        catch (e: UserException) {
            ctx.status(404)
            ctx.json(Error("error", e.message!!))
          }

    }

}