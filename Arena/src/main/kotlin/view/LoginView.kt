package view

import errors.LoginException
import org.github.unqui.UserException
import org.github.unqui.getSpotifyService
import org.uqbar.arena.Application
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.Window
import org.uqbar.arena.windows.WindowOwner
import viewmodel.LoginViewModel
import viewmodel.UserViewModel

fun main() = SpotifyAplication().start()

class SpotifyAplication() : Application(){
    override fun createMainWindow(): Window<*> {
        val spotifyService = getSpotifyService()
        return LoginView(this, LoginViewModel(spotifyService))
    }

}


class LoginView(owner: WindowOwner, model : LoginViewModel) : SimpleWindow<LoginViewModel>(owner,model) {


    override fun addActions(p0: Panel?) {

    }

    override fun createFormPanel(panelLogin: Panel) {
        title = "Spotify Login"
        setMinWidth(250)
        setMinHeight(100)

        Panel(panelLogin) with {
            width = 50
            asHorizontal()
            Label(it) with {
                text = "Email"
                width = 80
            }

            TextBox(it) with {
                bindTo("email")
                width = 150
            }
        }

        Panel(panelLogin) with {
            width = 50
            asHorizontal()

            Label(it) with {
                text = "Password"
                width = 80
            }
            PasswordField(it) with {
                bindTo("password")
                width = 150}
        }

        Button(panelLogin) with {
            setAsDefault()
            width = 100
            caption = "Login"
            onClick { procesarLogin()}
        }

    }

    fun procesarLogin(){
        try{
            var usuario = modelObject.validar()
            close()
            UserView(this, UserViewModel(usuario, modelObject.services),modelObject.services ).open()
        }
        catch (e: LoginException){ thisWindow.showError(e.message)}
        catch (e:UserException){thisWindow.showError(e.message)}
    }
}



