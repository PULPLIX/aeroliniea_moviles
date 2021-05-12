package com.example.aerolinea.Model

object Singleton{

    private var usuarios:MutableMap<String, Usuario> = mutableMapOf()

    init {
        // Aqui se inicializan las variables y metodos
    }

    fun addUser(key:String, usuario:Usuario):Boolean{
        usuarios.put(key,usuario)
        return true
    }
    fun findUser(key: String):Usuario?{
        return usuarios.get(key)
    }

    fun confirmUser(key: String,password: String):Boolean{
        if (usuarios.get(key)?.password.equals(password)){
            return true
        }
        return false
    }
}

fun main(args: Array<String>) {
    var a = model()
    // Aqui se genera la clase
}

class model {

    init {
        // Aqui se inicializan las variables o lo que se necesite en model
    }

    fun getInstance():Singleton{
        return Singleton
    }

}