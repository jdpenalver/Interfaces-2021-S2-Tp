package viewmodel
import SpotifyService
import org.github.unqui.EditUser
import org.github.unqui.User
import org.github.unqui.UserException
import org.uqbar.commons.model.annotations.Observable

@Observable
class EditUserViewModel (var model: UserViewModel, var userModel:User, var service: SpotifyService){
    var newName = userModel.displayName
    var newPass= userModel.password
    var newUrlImage= userModel.image


    fun saveData(){
        if(newName == "") throw UserException("Name could not be empty")
        if(newPass == "") throw UserException("Password could not be empty")
        var userDraft = EditUser(newUrlImage, newPass, newName)
        service.editUser(userModel.id, userDraft)
        model.updateAll()

    }
}