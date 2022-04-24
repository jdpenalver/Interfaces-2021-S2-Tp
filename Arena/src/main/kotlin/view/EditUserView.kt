package view
import org.github.unqui.UserException
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import viewmodel.EditUserViewModel


class EditUserView (owner : WindowOwner, model : EditUserViewModel ) : SimpleWindow<EditUserViewModel>(owner, model) {
    override fun addActions(p0: Panel?) {

    }

    override fun createFormPanel(panelLogin: Panel) {
        title = "Edit user information"
        setMinWidth(250)
        setMinHeight(100)

        Panel(panelLogin) with {
            width = 50
            asHorizontal()
            Label(it) with {
                text = "Name"
                width = 80
            }

            TextBox(it) with {
               bindTo("newName")
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
              bindTo("newPass")
                width = 150}
        }
        Panel(panelLogin) with {
            width = 50
            asHorizontal()

            Label(it) with {
                text = "Image"
                width = 80
            }
            TextBox(it) with {
                bindTo("newUrlImage")
                width = 150}
        }

        Button(panelLogin) with {
            setAsDefault()
            width = 45
            caption = "Accept"
            onClick {
                try {
                    modelObject.saveData()
                    showInfo("User Updated")
                    close()
                } catch (e : UserException){showError(e.message)}
            }
        }

        Button(panelLogin) with {
            width = 45
            caption = "Cancel"
            onClick { close()}
        }

    }


}