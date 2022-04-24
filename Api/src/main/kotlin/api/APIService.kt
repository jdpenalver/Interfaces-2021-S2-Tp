package org.example.api

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder
import io.javalin.apibuilder.ApiBuilder.path
import io.javalin.core.security.RouteRole
import org.example.api.controler.*
import org.github.unqui.getSpotifyService

enum class Roles : RouteRole {
    ANYONE, USER
}

class APIService {
    val spotifyService = getSpotifyService()
    val tokenJWT = TokenJWT()
    val jwtAccessManager = JWTAccessManager(spotifyService, tokenJWT)
    val registerController = RegisterController(spotifyService, tokenJWT)
    var loginController = LoginController(spotifyService, tokenJWT)
    var userController = UserController(spotifyService,tokenJWT)
    var playListController = PlaylistController(spotifyService, tokenJWT)
    var searchController = SearchController(spotifyService, tokenJWT)


    fun start(){

        val app = Javalin.create {
            it.defaultContentType = "application/json"
            it.accessManager(jwtAccessManager)
            it.enableCorsForAllOrigins()
        }.start(7000)
        app.before {
            it.header("Access-Control-Expose-Headers", "*")
        }

        app.routes {
            path("/register") {
                ApiBuilder.post(registerController::register, Roles.ANYONE)
            }
            path("/login"){
                ApiBuilder.post(loginController::login, Roles.ANYONE)
            }
            path("/user") {
                ApiBuilder.get(userController::getUser, Roles.USER)
                path("{id}"){
                    ApiBuilder.get(userController::getUserPorId, Roles.USER)
                }
            }

            path("/playlist"){
                path("{playlistId}") {
                    ApiBuilder.get(playListController::getPlayList, Roles.USER)
                    ApiBuilder.put(playListController::addOrRemoveLikeList, Roles.USER)
                }
            }
            path("/search"){
                ApiBuilder.get(searchController::searchBy, Roles.USER)
            }

        }

    }


}