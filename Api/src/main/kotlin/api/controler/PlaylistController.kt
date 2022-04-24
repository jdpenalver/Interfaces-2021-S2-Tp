package org.example.api.controler

import SpotifyService
import io.javalin.http.Context
import io.javalin.http.UnauthorizedResponse
import org.example.api.TokenJWT
import org.example.api.mapper.PlayListMapper
import org.example.api.mapper.UserMapper
import org.example.model.*
import org.github.unqui.PlaylistException
import org.github.unqui.UserException

class PlaylistController(var spotifyService: SpotifyService, var tokenJWT: TokenJWT) {

    fun getPlayList(ctx : Context) {
        try {
            val token = ctx.header("Authorization")
            tokenJWT.validate(token as String)
            val id = ctx.pathParam("playlistId")
            return getPlayId(ctx, id)
        }
        catch (e: NotFoundToken){
            throw UnauthorizedResponse("Token not found")
        }

    }

    private fun getPlayId(ctx : Context, id : String) {
        try {
            val playlistDTO = PlayListMapper().tranformarADto(spotifyService.getPlaylist(id))
            ctx.status(200)
            ctx.json(playlistDTO)
        }
        catch (e: PlaylistException){
            ctx.status(404)
            ctx.json( Error("Not found playlist with id $id", e.message!!) )
        }
    }

    fun addOrRemoveLikeList(ctx: Context){
        try {
            val token = ctx.header("Authorization")
            val userId = tokenJWT.validate(token as String)
            val idPlaylist = ctx.pathParam("playlistId")
            updateLike(userId, idPlaylist, ctx)
        }
        catch (e : NotFoundToken){
            throw UnauthorizedResponse("Token not found")
        }
    }

    private fun updateLike(userId: String, idPlaylist: String, ctx: Context) {
        try {
            spotifyService.addOrRemoveLike(userId, idPlaylist)
            ctx.status(200)
            ctx.json(UserMapper().toUserDTO(spotifyService.getUser(userId)))
        }
        catch (e: UserException){
            ctx.status(404)
            ctx.json(Error("Not found user", e.message!!))
        }
        catch (e: PlaylistException) {
            ctx.status(404)
            ctx.json(Error("Not found playlist with id $idPlaylist", e.message!!))
        }
    }

}