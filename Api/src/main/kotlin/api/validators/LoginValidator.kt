package org.example.api.validators

class LoginValidator(var email: String, var password: String) {
    fun verificarComplegidadPassWord():Boolean{
        return password.length >= 1
    }

    fun verificarEmail():Boolean{
        return email.contains("@") && email.contains(".")
    }
}