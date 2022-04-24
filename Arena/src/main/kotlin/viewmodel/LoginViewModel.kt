package viewmodel

import SpotifyService
import errors.LoginException
import org.github.unqui.User
import org.uqbar.commons.model.annotations.Observable

@Observable
class LoginViewModel(var services : SpotifyService) {

    var password = "a"
    var email = "a@gmail.com"

    fun validar() : User{
        if(email == "" || password == "") throw LoginException("Please complete user and password")
        return services.login(email,password)
    }




}




