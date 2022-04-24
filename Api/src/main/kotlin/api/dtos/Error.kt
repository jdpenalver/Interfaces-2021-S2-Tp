package org.example.model

class Error(var result: String, var message: String)
class NotFoundToken:Exception("Error al registrar")
