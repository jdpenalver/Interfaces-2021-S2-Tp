package org.example.api.controler

import SpotifyService
import io.javalin.http.Context
import org.example.api.TokenJWT
import org.example.api.mapper.UserMapper
import org.example.api.validators.LoginValidator
import org.example.model.Error
import org.github.unqui.UserException

class LoginController(var spotifyService: SpotifyService, var tokenJWT: TokenJWT) {

    fun login(ctx: Context) {
        try {
            val login = ctx.bodyValidator<LoginValidator>()
                .check(LoginValidator::verificarEmail, "El e-mail debe contener @ y punto")
                .check(LoginValidator::verificarComplegidadPassWord, "La contraseña no cumple con los minimos necesarios: Mayor a 1 cáracter")
                .get()
            val user = spotifyService.login(login.email, login.password)
            ctx.header("Authorization", tokenJWT.generateToken(user))
            ctx.status(200)
            ctx.json( UserMapper().toUserDTO(user))
        } catch (e: UserException) {
            ctx.status(404)
            ctx.json( Error("error", e.message!!) )
        }
    }
}
