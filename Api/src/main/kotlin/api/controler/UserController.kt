package org.example.api.controler

import SpotifyService
import io.javalin.http.Context
import io.javalin.http.UnauthorizedResponse
import org.example.api.TokenJWT
import org.example.api.mapper.UserMapper
import org.example.model.Error
import org.example.model.NotFoundToken
import org.github.unqui.UserException


class UserController(val spotifyService: SpotifyService, val tokenJWT: TokenJWT) {

    fun validar(token: String) {
        try {
            tokenJWT.validate(token)
        } catch (e: NotFoundToken) {
            throw UnauthorizedResponse("Token not found")
        }
    }

    fun getUser(ctx: Context){
        val token = ctx.header("Authorization")
        val userId = tokenJWT.validate(token as String)
        return userFromSpoty(ctx, userId)
    }

    fun getUserPorId(ctx: Context){
        val userId = ctx.pathParam("id")
        userFromSpoty(ctx, userId)
    }

    private fun userFromSpoty(ctx: Context, id:String){
        try {
            var user = spotifyService.getUser(id)
            ctx.header("Authorization", tokenJWT.generateToken(user))
            ctx.status(200)
            ctx.json( UserMapper().toUserDTO(user))
        } catch (e: UserException) {
            ctx.status(404)
            ctx.json( Error("Not found user with id $id", e.message!!) )
        }
    }

}