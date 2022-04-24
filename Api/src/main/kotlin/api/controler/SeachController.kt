package org.example.api.controler
import SpotifyService
import io.javalin.http.*
import io.javalin.http.UnauthorizedResponse
import org.example.api.TokenJWT
import org.example.api.mapper.SearchMapper
import org.example.model.NotFoundToken


class SearchController(var spotifyService:SpotifyService, var tokenJWT: TokenJWT){

    fun searchBy (ctx: Context) {

        try {
            val token = ctx.header("Authorization")
            tokenJWT.validate(token as String)
            var value=  ctx.queryParam("q")
            return seachInModel(ctx, value!!)
        } catch (e: NotFoundToken) {
            throw UnauthorizedResponse("Token not found")
        }
    }

    fun seachInModel(ctx:Context, valorABuscar:String){
        var seachResults = SearchMapper()
        seachResults.addUser(spotifyService.searchUser(valorABuscar))
        seachResults.addSong(spotifyService.searchSong(valorABuscar))
        seachResults.addPlayList( spotifyService.searchPlaylist(valorABuscar))
        ctx.status(200)
        ctx.json(seachResults)
    }
}
