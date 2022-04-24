package org.example.api

import SpotifyService
import io.javalin.core.security.AccessManager
import io.javalin.core.security.RouteRole
import io.javalin.http.Context
import io.javalin.http.Handler
import io.javalin.http.UnauthorizedResponse
import org.example.api.controler.UserController
import org.github.unqui.User


class JWTAccessManager(val spotifyService: SpotifyService,val tokenJWT: TokenJWT, ): AccessManager {

    override fun manage(handler: Handler, ctx: Context, roles: MutableSet<RouteRole>) {
        val token = ctx.header("Authorization")
        when {
            token == null && roles.contains(Roles.ANYONE) -> handler.handle(ctx)
            token == null -> throw UnauthorizedResponse("Token not found")
            roles.contains(Roles.ANYONE) -> handler.handle(ctx)
            roles.contains(Roles.USER) -> {
                UserController(spotifyService,tokenJWT).validar(token)
                handler.handle(ctx)
            }
        }
    }
}